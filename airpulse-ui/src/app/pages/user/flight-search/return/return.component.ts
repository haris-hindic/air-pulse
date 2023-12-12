import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FlightResponse, FlightSearchRequest } from 'src/app/pages/admin/model/flight.model';
import { RouteResponse } from 'src/app/pages/admin/model/route.model';
import { FlightService } from 'src/app/pages/admin/services/flight.service';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';

@Component({
  selector: 'app-return',
  templateUrl: './return.component.html',
  styleUrls: ['./return.component.css']
})
export class ReturnComponent {

  flightId!: number;
  flight!: FlightResponse;
  returnFlights!: FlightResponse[];
  searchRequest: FlightSearchRequest = new FlightSearchRequest();

  returnAt!: any;
  departOn!: any;

  routesLov: any[] = [];

  constructor(private flightService: FlightService,
    private route: ActivatedRoute,
    private messageToast: MessageToast, private loader: LoaderService) {
  }

  ngOnInit() {
    this.loadData();
  }


  loadFlights(search: any) {
    this.flightService.getAll(search).subscribe(
      {
        next: (result) => {
          this.returnFlights = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  searchFlights() {
    if (this.departOn != null && this.departOn !== '') {
      this.searchRequest.departOn = new Date(this.departOn.getTime() - (this.departOn.getTimezoneOffset() * 60000)).toJSON().replace('Z', '');
    } else {
      this.searchRequest.flightAfter = new Date(this.flight.departure).toJSON().replace('Z', '');
    }
    this.loadReturnFlights(this.flight.route);
  }

  clearFilter() {
    this.departOn = null; this.searchRequest.departOn = '';
  }

  loadData() {
    this.flightId = this.route.snapshot.paramMap.get('id') as unknown as number;
    this.flightService.getById(this.flightId).subscribe({
      next: result => {
        this.flight = result;
        this.routesLov.push({ label: result.route.departureAirportDetails + "-" + result.route.arrivalAirportDetails, value: result.route.id });
        this.searchRequest.routeId = result.route.id;
        this.searchRequest.flightAfter = new Date(this.flight.departure).toJSON().replace('Z', '');
        this.loadReturnFlights(this.flight.route);
      },
      error: (err) => {
        this.handleError(err);
      },
    });
  }

  loadReturnFlights(route: RouteResponse) {
    this.flightService.findReturnFlights({
      departureAirportId: route.arrivalAirportId, arrivalAirportId: route.departureAirportId, flightAfter: this.searchRequest.flightAfter,
      departOn: this.searchRequest.departOn
    }).subscribe({
      next: result => {
        this.returnFlights = result;
      },
      error: (err) => {
        this.handleError(err);
      },
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
