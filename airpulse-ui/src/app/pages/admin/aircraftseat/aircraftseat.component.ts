import { Component, Input } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { AircraftResponse } from '../model/aircraft.model';
import { AircraftseatResponse, AircraftseatRequest } from '../model/aircraftseat.model';
import { AircraftSeatService } from '../services/aircraftseat.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-aircraftseat',
  templateUrl: './aircraftseat.component.html',
  styleUrls: ['./aircraftseat.component.css']
})
export class AircraftseatComponent {

  @Input() aircraft!: AircraftResponse;

  aircraftseatDialog: boolean = false;

  aircraftseats!: AircraftseatResponse[];

  aircraftseat!: AircraftseatResponse;

  selectedAircraftseats!: any[] | null;

  seatClasses = [
    { label: 'ECONOMY', value: 'Economy' },
    { label: 'BUSINESS', value: 'Business' },
    { label: 'FIRST CLASS', value: 'First Class' },
    { label: 'PREMIUM ECONOMY', value: 'Premium Economy' }
  ];

  availabelSeatClasses!: any[];

  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private aircraftseatService: AircraftSeatService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.aircraftseats = [];
    this.aircraftseatService.getByAircraftId(this.aircraft.id).subscribe(
      {
        next: (result) => {
          this.aircraftseats = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.setAvailableSeatClasses();
    if (this.availabelSeatClasses.length === 0) {
      return this.messageToast.showWarn('Error', 'All available seat classes are already assigned.');
    }
    this.aircraftseat = new AircraftseatResponse();
    this.aircraftseatDialog = true;
  }

  setAvailableSeatClasses() {
    this.availabelSeatClasses = [];
    this.availabelSeatClasses = this.seatClasses.filter(x => !this.aircraftseats.some(y => y.seatClass === x.value));
  }

  deleteSelectedAircraftseats() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected aircraftseats?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.aircraftseatService.bulkDelete({ ids: this.selectedAircraftseats!.map(x => x.id) }).subscribe({
          next: result => {
            this.selectedAircraftseats = null;
            this.messageToast.showSuccess('Successful', 'Aircraft seats deleted.');
            this.loader.hide();
            this.findAll();
          },
          error: err => {
            this.handleError(err);
          }
        });
      }
    });
  }

  editAircraftseat(aircraftseat: any) {
    this.aircraftseat = { ...aircraftseat };
    this.aircraftseatDialog = true;
  }

  deleteAircraftseat(aircraftseat: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete selected absece?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.aircraftseatService.delete(aircraftseat.id).subscribe({
          next: result => {
            this.selectedAircraftseats = null;
            this.messageToast.showSuccess('Successful', 'Aircraft seat deleted.');
            this.loader.hide();
            this.findAll();
          },
          error: err => {
            this.handleError(err);
          }
        });
      }
    });
  }

  hideDialog() {
    this.aircraftseatDialog = false;
  }

  saveAircraftseat(aircraftseatRequest: AircraftseatRequest) {

    if (this.aircraftseat.id) {
      this.aircraftseatService.update(this.aircraftseat.id, aircraftseatRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Aircraft seat updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    } else {
      this.aircraftseatService.create(aircraftseatRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Aircraft seat created.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    }
  }

  handleError(error: any) {
    try {
      this.messageToast.showError(
        error['error']['status'],
        error['error']['errors'] || error['error']['error']
      );
    } catch (err) {
      this.messageToast.showError(
        'Error',
        error
      );
    }
  }

  getSeverity(status: string) {
    switch (status) {
      case 'APPROVED':
        return 'success';
      case 'REJECTED':
        return 'danger';
      case 'PENDING':
        return 'warning';
    }

    return 'N/A';
  }
}
