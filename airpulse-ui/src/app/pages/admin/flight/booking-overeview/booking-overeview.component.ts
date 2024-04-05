import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FlightBookingService } from '../../services/flightbooking.service';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { FlightBookingResponse } from '../../model/flightbooking.model';

@Component({
  selector: 'app-booking-overeview',
  templateUrl: './booking-overeview.component.html',
  styleUrls: ['./booking-overeview.component.css']
})
export class BookingOvereviewComponent {
  @Input()
  visible!: boolean;

  @Input()
  flightId!: number;
  @Input()
  flightBookings!: FlightBookingResponse[];

  @Output()
  hideButtonClicked = new EventEmitter<any>();




  constructor(private flightBookingService: FlightBookingService,
    private loader: LoaderService) {
  }

  ngOnInit() {
    this.loadData();
  }

  ngOnChanges() {
    this.loadData();
  }

  loadData() {
    this.loadBookings();
  }

  loadBookings() {
    this.flightBookings = [];
    this.flightBookingService.getAll({ 'flightId': this.flightId, 'status': 'Confirmed' }).subscribe(
      {
        next: (result) => {
          this.flightBookings = result;
        }, error: (error) => {
          this.loader.hide();
        }
      }
    );
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

  getSeatClass(seatClass: string) {
    switch (seatClass) {
      case '1':
        return 'Economy';
      case '2':
        return 'Business';
      case '2.5':
        return 'First Class';
      case '1.5':
        return 'Premium Economy';
    }

    return 'N/A';
  }

  getLuggage(seatClass: string) {
    switch (seatClass) {
      case '1':
        return 'luggage (20kg) + carry on (8kg)';
      case '1.1':
        return 'luggage (30kg) + carry on (8kg)';
      case '1.2':
        return 'luggage (40kg) + carry on (8kg)';
      case '1.3':
        return 'luggage (40kg) + carry on (12kg)';
    }

    return 'N/A';
  }

  hideDialog() { this.hideButtonClicked.emit(); }
}
