import { Component } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { AircraftResponse, AircraftRequest } from '../model/aircraft.model';
import { AircraftService } from '../services/aircraft.service';

@Component({
  selector: 'app-aircraft',
  templateUrl: './aircraft.component.html',
  styleUrls: ['./aircraft.component.css']
})
export class AircraftComponent {
  aircraftDialog: boolean = false;

  aircrafts!: AircraftResponse[];

  aircraft!: AircraftResponse;

  selectedAircrafts!: any[] | null;


  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private aircraftService: AircraftService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.aircrafts = [];
    this.aircraftService.getAll().subscribe(
      {
        next: (result) => {
          this.aircrafts = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.aircraft = new AircraftResponse();
    this.aircraftDialog = true;
  }

  deleteSelectedAircrafts() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected aircrafts?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.aircraftService.bulkDelete({ ids: this.selectedAircrafts!.map(x => x.id) }).subscribe({
          next: result => {
            this.selectedAircrafts = null;
            this.messageToast.showSuccess('Successful', 'aircrafts deleted.');
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

  editAircraft(aircraft: any) {
    this.aircraft = { ...aircraft };
    this.aircraftDialog = true;
  }

  deletAaircraft(aircraft: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + aircraft.firstName + " " + aircraft.lastName + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.aircraftService.delete(aircraft.id).subscribe({
          next: result => {
            this.messageToast.showSuccess('Successful', 'aircraft deleted.');
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
    this.aircraftDialog = false;
  }

  saveAircraft(aircraftRequest: AircraftRequest) {

    if (this.aircraft.id) {
      this.aircraftService.update(this.aircraft.id, aircraftRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Aircraft updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      })
    } else {
      this.aircraftService.create(aircraftRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Aircraft created.');
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
      case 'ACTIVE':
        return 'success';
      case 'INACTIVE':
        return 'danger';
    }

    return 'N/A';
  }
}
