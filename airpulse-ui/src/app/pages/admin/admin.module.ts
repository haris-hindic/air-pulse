import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { RouterModule } from "@angular/router";
import { AdminComponent } from "./admin.component";
import { SideMenuComponent } from './side-menu/side-menu.component';
import { SidebarModule } from 'primeng/sidebar';
import { MenuModule } from 'primeng/menu';
import { EmployeeOverviewComponent } from './employee/employee-overview/employee-overview.component';
import { AircraftOverviewComponent } from './aircraft/aircraft-overview/aircraft-overview.component';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild([
            {
                path: '', component: AdminComponent, children: [
                    { path: '', component: DashboardComponent },
                    { path: 'employee', component: EmployeeOverviewComponent },
                    { path: 'aircraft', component: AircraftOverviewComponent }
                ]
            },

            //{ path: '**', redirectTo: '/notfound' }
        ]),
        SidebarModule,
        MenuModule
    ],
    declarations: [
        AdminComponent, SideMenuComponent, EmployeeOverviewComponent, AircraftOverviewComponent, DashboardComponent
    ],
    exports: []
})
export class AdminModule { }