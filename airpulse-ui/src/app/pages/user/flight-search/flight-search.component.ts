import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { RouteService } from '../../admin/services/route.service';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { FlightResponse } from '../../admin/model/flight.model';
import { FlightService } from '../../admin/services/flight.service';

@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent {

  routesLov!: any[];

  form!: FormGroup;

  flights!: FlightResponse[];

  searchRequest: { routeId: number | null, departOn: Date | null, status: string } = {
    routeId: null,
    departOn: null,
    status: 'Active'
  };

  seatClasses = [
    { label: 'ECONOMY', value: 'Economy' },
    { label: 'BUSINESS', value: 'Business' },
    { label: 'FIRST CLASS', value: 'First Class' },
    { label: 'PREMIUM ECONOMY', value: 'Premium Economy' }
  ];

  constructor(private formBuilder: FormBuilder, private routeService: RouteService, private messageToast: MessageToast,
    private loader: LoaderService, private flightService: FlightService) {
  }



  ngOnInit() {
    this.loadData();
  }

  loadData() {
    this.loadRoutes();
    this.loadFlights({ status: 'Active' });
  }

  loadRoutes() {
    this.routesLov = [];

    this.routeService.getAll().subscribe(
      {
        next: (result) => {
          this.routesLov = result.map(x => {
            return { label: x.departureAirportDetails + "-" + x.arrivalAirportDetails, value: x.id };
          });
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  loadFlights(search: any) {

    this.flightService.getAll(search).subscribe(
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



  searchFlights() {
    this.searchRequest.status = 'Active';
    if (this.searchRequest.departOn) {
      this.searchRequest.departOn = new Date(this.searchRequest.departOn);
    }
    this.loadFlights(this.searchRequest);
  }

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
    }

    return 'N/A';
  }
}