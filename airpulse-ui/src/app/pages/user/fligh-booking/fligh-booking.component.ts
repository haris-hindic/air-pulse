import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { SecurityService } from '../../auth/services/security.service';
import { UserService } from '../services/user.service';
import { UserResponse } from '../../auth/model/user';
import { MessageToast } from '../../shared/services/message-toast.service';
import { FlightResponse } from '../../admin/model/flight.model';
import { FlightService } from '../../admin/services/flight.service';

@Component({
  selector: 'app-fligh-booking',
  templateUrl: './fligh-booking.component.html',
  styleUrls: ['./fligh-booking.component.css']
})
export class FlighBookingComponent {


  departFlightId!: number;
  returnFlightId!: number;

  constructor(private route: ActivatedRoute) {

  }

  ngOnInit() {
    this.departFlightId = this.route.snapshot.paramMap.get('departFlightId') as unknown as number;
    this.returnFlightId = this.route.snapshot.paramMap.get('returnFlightId') as unknown as number;
  }

  items = [
    {
      label: 'Details',
      routerLink: `details`
    },
    // {
    //   label: 'Confirmation',
    //   routerLink: 'confirmation'
    // },
    {
      label: 'Payment',
      routerLink: `payment`
    },
  ];
}
