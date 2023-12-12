import { Component, Input } from '@angular/core';
import { FlightResponse } from 'src/app/pages/admin/model/flight.model';

@Component({
  selector: 'app-flight-list',
  templateUrl: './flight-list.component.html',
  styleUrls: ['./flight-list.component.css']
})
export class FlightListComponent {
  @Input() firstFlight!: FlightResponse;
  @Input() flights!: FlightResponse[];
  @Input() singleReturnFlight!: boolean;
  @Input() returnFlights!: boolean;
}
