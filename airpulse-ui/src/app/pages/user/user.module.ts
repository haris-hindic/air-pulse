import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { UseradminComponent } from "./useradmin/useradmin.component";
import { RouterModule } from "@angular/router";
import { DetailsCardComponent } from './useradmin/details-card/details-card.component';
import { DetailsPanelComponent } from './useradmin/details-panel/details-panel.component';
import { AuthModule } from "../auth/auth.module";
import { UserFormComponent } from './useradmin/user-form/user-form.component';
import { FlightSearchComponent } from './flight-search/flight-search.component';
import { FlightListComponent } from './flight-search/flight-list/flight-list.component';
import { ReturnComponent } from './flight-search/return/return.component';
import { FlighBookingComponent } from './fligh-booking/fligh-booking.component';
import { StepsModule } from 'primeng/steps';
import { DetailsComponent } from './fligh-booking/details/details.component';
import { ConfirmationComponent } from './fligh-booking/confirmation/confirmation.component';
import { PaymentComponent } from './fligh-booking/payment/payment.component';
import { SuccessComponent } from './fligh-booking/success/success.component';
import { BookedFlightsComponent } from './useradmin/booked-flights/booked-flights.component';
import { FlightPreviewComponent } from './useradmin/booked-flights/flight-preview/flight-preview.component';


@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild([
            {
                path: '', component: UseradminComponent, children: []
            },
            {
                path: 'search', component: FlightSearchComponent, children: []
            },
            {
                path: 'search/return/:id', component: ReturnComponent, children: []
            },
            {
                path: 'booking/:departFlightId/:returnFlightId', component: FlighBookingComponent, children: [
                    { path: 'details', component: DetailsComponent },
                    { path: 'payment', component: PaymentComponent },
                    { path: 'confirmation', component: ConfirmationComponent },
                ]
            },
            {
                path: 'booking/:departFlightId', component: FlighBookingComponent, children: [
                    { path: 'details', component: DetailsComponent },
                    { path: 'payment', component: PaymentComponent },
                    { path: 'confirmation', component: ConfirmationComponent },
                ]
            },
            {
                path: 'success/:bookedFlightId', component: SuccessComponent
            },

            //{ path: '**', redirectTo: '/notfound' }
        ]),
        AuthModule,
        StepsModule
    ],
    declarations: [
        UseradminComponent,
        DetailsCardComponent,
        DetailsPanelComponent,
        UserFormComponent,
        FlightSearchComponent,
        FlightListComponent,
        ReturnComponent,
        FlighBookingComponent,
        DetailsComponent,
        ConfirmationComponent,
        PaymentComponent,
        SuccessComponent,
        BookedFlightsComponent,
        FlightPreviewComponent
    ],
    exports: []
})
export class UserModule { }