import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FlightResponse } from 'src/app/pages/admin/model/flight.model';
import { FlightService } from 'src/app/pages/admin/services/flight.service';
import { UserResponse } from 'src/app/pages/auth/model/user';
import { SecurityService } from 'src/app/pages/auth/services/security.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { UserService } from '../../services/user.service';
import { DropdownChangeEvent } from 'primeng/dropdown';

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
    { label: 'ECONOMY', value: 'Economy' },
    { label: 'BUSINESS', value: 'Business' },
    { label: 'FIRST CLASS', value: 'First Class' },
    { label: 'PREMIUM ECONOMY', value: 'Premium Economy' }
  ];

  constructor(private route: ActivatedRoute,
    private securityService: SecurityService,
    private userService: UserService,
    private messageToast: MessageToast,
    private flightService: FlightService,
    private router: Router) {

  }

  ngOnInit() {
    this.departFlightId = this.route.snapshot.parent?.paramMap.get('departFlightId') as unknown as number;
    this.returnFlightId = this.route.snapshot.parent?.paramMap.get('returnFlightId') as unknown as number;
    this.loadDepartFlight(this.departFlightId);
    if (this.returnFlightId) {
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
    console.log('event :>> ', event);
    switch (event.value) {
      case 'First Class':
        this.totalPrice = this.basePrice * 2.5;
        break;
      case 'Business':
        this.totalPrice = this.basePrice * 2;
        break;
      case 'Premium Economy':
        this.totalPrice = this.basePrice * 1.5;
        break;
      case 'Economy':
        this.totalPrice = this.basePrice;
        break;
    }
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

  nextPage() {
    this.router.navigate([`/user/booking/${this.departFlightId}/${this.returnFlightId}/payment`]);
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
