import { Component, Input } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { FlightBookingRequest, FlightBookingResponse } from 'src/app/pages/admin/model/flightbooking.model';
import { FlightBookingService } from 'src/app/pages/admin/services/flightbooking.service';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';

@Component({
  selector: 'app-booked-flights',
  templateUrl: './booked-flights.component.html',
  styleUrls: ['./booked-flights.component.css']
})
export class BookedFlightsComponent {
  @Input() userId!: number;

  flightBookingDialog: boolean = false;

  flightPreviewDialog: boolean = false;

  flightBookings!: FlightBookingResponse[];

  flightBooking!: FlightBookingResponse;

  selectedFlightBookings!: any | null;

  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private flightBookingService: FlightBookingService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.flightBookings = [];
    this.flightBookingService.getAll({ 'userId': this.userId }).subscribe(
      {
        next: (result) => {
          this.flightBookings = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.flightBooking = this.selectedFlightBookings[0];
    this.flightBookingDialog = true;
  }

  deactivateSelectedProducts() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to cancel the selected booking?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //BULK DELETE
        this.flightBookingService.cancelBooking(this.selectedFlightBookings[0].id).subscribe({
          next: result => {
            this.selectedFlightBookings = null;
            this.messageToast.showSuccess('Successful', result);
            this.findAll();
          },
          error: (err) => {
            this.handleError(err);
          }
        });

      }
    });
  }


  hideDialog() {
    this.flightBookingDialog = false;
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
      case 'CONFIRMED':
        return 'success';
      case 'CANCELLED':
        return 'danger';
      case 'DRAFT':
        return 'warning';
    }

    return 'N/A';
  }
}
