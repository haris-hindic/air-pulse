import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { UseradminComponent } from "./useradmin/useradmin.component";
import { RouterModule } from "@angular/router";
import { DetailsCardComponent } from './useradmin/details-card/details-card.component';
import { DetailsPanelComponent } from './useradmin/details-panel/details-panel.component';
import { RegisterFormComponent } from "../auth/register/register-form/register-form.component";
import { AuthModule } from "../auth/auth.module";
import { UserFormComponent } from './useradmin/user-form/user-form.component';
import { FlightSearchComponent } from './flight-search/flight-search.component';
import { FlightListComponent } from './flight-search/flight-list/flight-list.component';


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

            //{ path: '**', redirectTo: '/notfound' }
        ]),
        AuthModule
    ],
    declarations: [
        UseradminComponent,
        DetailsCardComponent,
        DetailsPanelComponent,
        UserFormComponent,
        FlightSearchComponent,
        FlightListComponent
    ],
    exports: []
})
export class UserModule { }