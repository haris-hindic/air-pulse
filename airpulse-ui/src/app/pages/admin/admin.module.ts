import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { RouterModule } from "@angular/router";
import { AdminComponent } from "./admin.component";
import { SideMenuComponent } from './side-menu/side-menu.component';
import { SidebarModule } from 'primeng/sidebar';
import { EmployeeOverviewComponent } from './employee/employee-overview/employee-overview.component';
import { AircraftOverviewComponent } from './aircraft/aircraft-overview/aircraft-overview.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { JobtypeComponent } from './jobtype/jobtype.component';

@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild([
            {
                path: '', component: AdminComponent, children: [
                    { path: '', component: DashboardComponent },
                    { path: 'employee', component: EmployeeOverviewComponent },
                    { path: 'aircraft', component: AircraftOverviewComponent },
                    { path: 'jobtype', component: JobtypeComponent }
                ]
            },

            //{ path: '**', redirectTo: '/notfound' }
        ]),
        SidebarModule
    ],
    declarations: [
        AdminComponent, SideMenuComponent, EmployeeOverviewComponent, AircraftOverviewComponent, DashboardComponent, JobtypeComponent
    ],
    exports: []
})
export class AdminModule { }