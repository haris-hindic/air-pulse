import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FlightBookingService } from 'src/app/pages/admin/services/flightbooking.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.css']
})
export class SuccessComponent implements OnInit {

  bookingId!: number;
  constructor(private route: ActivatedRoute, private flightBookingService: FlightBookingService,
    private messageToast: MessageToast) {

  }

  ngOnInit(): void {
    this.bookingId = this.route.snapshot.paramMap.get('bookedFlightId') as unknown as number;
    this.flightBookingService.confirmBooking(this.bookingId).subscribe({
      next: result => {
        this.messageToast.showSuccess("Success", result);
      },
      error: (err) => {
        this.handleError(err);
      }
    });
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
}
