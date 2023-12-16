import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FlightResponse } from 'src/app/pages/admin/model/flight.model';
import { FlightService } from 'src/app/pages/admin/services/flight.service';
import { UserResponse } from 'src/app/pages/auth/model/user';
import { SecurityService } from 'src/app/pages/auth/services/security.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { UserService } from '../../services/user.service';
import { DropdownChangeEvent } from 'primeng/dropdown';
import { switchMap } from 'rxjs';
import { StripeService } from 'ngx-stripe';

@Component({
  selector: 'app-details',
  templateUrl: './details.component.html',
  styleUrls: ['./details.component.css']
})
export class DetailsComponent {

  returnFlightId!: number;
  returnFlight!: FlightResponse;
  departFlightId!: number;
  departFlight!: FlightResponse;
  userId!: number;
  user!: UserResponse;
  totalPrice: number = 0;
  basePrice: number = 0;

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

  constructor(private route: ActivatedRoute,
    private securityService: SecurityService,
    private userService: UserService,
    private messageToast: MessageToast,
    private flightService: FlightService,
    private router: Router,
    private stripeService: StripeService) {

  }

  ngOnInit() {
    this.departFlightId = this.route.snapshot.paramMap.get('departFlightId') as unknown as number;
    this.returnFlightId = this.route.snapshot.paramMap.get('returnFlightId') as unknown as number;
    this.loadDepartFlight(this.departFlightId);
    if (this.returnFlightId && !isNaN(this.returnFlightId)) {
      this.loadReturnFlight(this.returnFlightId);
    }
    this.loadUser();
    this.totalPrice = this.basePrice;
  }

  loadUser() {
    this.userId = this.securityService.getFieldFromJWT("id");
    this.userService.getById(this.userId).subscribe({
      next: result => {
        this.user = result;
      },
      error: (err) => {
        this.handleError(err);
      },
    });
  }

  recalculatePrice(event: DropdownChangeEvent) {
    this.totalPrice = this.basePrice * this.selectedLuggage * this.selectedSeatClass;
  }

  recalculatePriceLuggage(event: DropdownChangeEvent) {
    console.log('event :>> ', event);
    this.totalPrice = this.basePrice * event.value;
  }

  loadDepartFlight(id: number) {
    this.flightService.getById(id).subscribe({
      next: result => {
        this.departFlight = result;
        this.basePrice += result.basePrice;
        this.totalPrice += result.basePrice;
      },
      error: (err) => {
        this.handleError(err);
      }
    });
  }

  loadReturnFlight(id: number) {
    this.flightService.getById(id).subscribe({
      next: result => {
        this.returnFlight = result;
        this.basePrice += result.basePrice;
        this.totalPrice += result.basePrice;
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

  checkout() {
    this.flightService.createCheckout({
      name: this.departFlight.route.departureAirportDetails + " " + this.departFlight.route.arrivalAirportDetails,
      amount: this.totalPrice,
      succesUrl: 'http://localhost:4200/user/bookign/success/' + this.departFlightId + '/' + this.totalPrice + '/' + this.userId,
      failUrl: 'http://localhost:4200/failure',
    }).subscribe(
      {
        next: result => {
          window.location.href = result;
        },
        error: (err) => {
          this.handleError(err);
        }
      },
    );
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
