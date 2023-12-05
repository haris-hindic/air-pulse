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
import { JobtypeFormComponent } from './jobtype/jobtype-form/jobtype-form.component';
import { EmployeeComponent } from './employee/employee.component';
import { EmployeeFormComponent } from './employee/employee-form/employee-form.component';
import { AbsenceComponent } from './absence/absence.component';
import { AbsenceFormComponent } from './absence/absence-form/absence-form.component';
import { PositionComponent } from './position/position.component';
import { PositionFormComponent } from './position/position-form/position-form.component';

@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild([
            {
                path: '', component: AdminComponent, children: [
                    { path: 'dashboard', component: DashboardComponent },
                    { path: 'employee', component: EmployeeComponent },
                    { path: 'employee/:id', component: EmployeeOverviewComponent },
                    { path: 'aircraft', component: AircraftOverviewComponent },
                    { path: 'jobtype', component: JobtypeComponent },
                    { path: '**', redirectTo: 'dashboard' },
                ]
            },

            //{ path: '**', redirectTo: '/notfound' }
        ]),
        SidebarModule
    ],
    declarations: [
        AdminComponent, SideMenuComponent, EmployeeOverviewComponent, AircraftOverviewComponent, DashboardComponent, JobtypeComponent, JobtypeFormComponent, EmployeeComponent, EmployeeFormComponent, AbsenceComponent, AbsenceFormComponent, PositionComponent, PositionFormComponent
    ],
    exports: []
})
export class AdminModule { }