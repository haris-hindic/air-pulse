import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FlightResponse } from 'src/app/pages/admin/model/flight.model';
import { FlightBookingResponse } from 'src/app/pages/admin/model/flightbooking.model';

@Component({
  selector: 'app-flight-preview',
  templateUrl: './flight-preview.component.html',
  styleUrls: ['./flight-preview.component.css']
})
export class FlightPreviewComponent {
  @Input()
  visible!: boolean;
  @Input()
  flight!: FlightBookingResponse;
  @Output()
  hideButtonClicked = new EventEmitter<any>();

  departureFlight!: FlightResponse;
  returnFlight!: FlightResponse;

  ngOnInit() {
    console.log('this.flight :>> ', this.flight);
    if (this.flight) {
      this.departureFlight = this.flight.flight;
      if (this.flight.returnFlightId) {
        this.returnFlight = this.flight.returnFlight;
      }
    }
  }

  seatClasses = [
    { label: 'ECONOMY', value: 1 },
    { label: 'BUSINESS', value: 2 },
    { label: 'FIRST CLASS', value: 2.5 },
    { label: 'PREMIUM ECONOMY', value: 1.5 }
  ];

  luggage = [
    { label: 'luggage (20kg) + carry on (8kg)', value: 1 },
    { label: 'luggage (30kg) + carry on (8kg)', value: 1.1 },
    { label: 'luggage (40kg) + carry on (8kg)', value: 1.2 },
    { label: 'luggage (40kg) + carry on (12kg)', value: 1.3 }
  ];
  selectedLuggage: any = 1;
  selectedSeatClass: any = 1;

  getSeverity(status: string) {
    switch (status) {
      case 'FIRST CLASS':
        return 'success';
      case 'BUSINESS':
        return 'info';
      case 'PREMIUM ECONOMY':
        return 'warning';
      case 'ECONOMY':
        return 'danger';
      default:
        return 'N/A';
    }
  }

  hideDialog() { this.hideButtonClicked.emit(); }
}
