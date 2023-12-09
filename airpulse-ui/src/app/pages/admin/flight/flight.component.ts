import { Component } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { FlightResponse, FlightRequest } from '../model/flight.model';
import { FlightService } from '../services/flight.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-flight',
  templateUrl: './flight.component.html',
  styleUrls: ['./flight.component.css']
})
export class FlightComponent {
  flightDialog: boolean = false;

  flights!: FlightResponse[];

  flight!: FlightResponse;

  selectedFlights!: any[] | null;


  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private flightService: FlightService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.flights = [];
    this.flightService.getAll().subscribe(
      {
        next: (result) => {
          this.flights = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.flight = new FlightResponse();
    this.flightDialog = true;
  }

  deleteSelectedFlights() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected flights?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.flightService.bulkDelete({ ids: this.selectedFlights!.map(x => x.id) }).subscribe({
          next: result => {
            this.selectedFlights = null;
            this.messageToast.showSuccess('Successful', 'Flights deleted.');
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

  editFlight(flight: any) {
    this.flight = { ...flight };
    this.flightDialog = true;
  }

  deleteFlight(flight: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected flight?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.flightService.delete(flight.id).subscribe({
          next: result => {
            this.messageToast.showSuccess('Successful', 'Flight deleted.');
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
    this.flightDialog = false;
  }

  saveFlight(flightRequest: FlightRequest) {

    if (this.flight.id) {
      this.flightService.update(this.flight.id, flightRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Flight updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    } else {
      this.flightService.create(flightRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Flight created.');
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
