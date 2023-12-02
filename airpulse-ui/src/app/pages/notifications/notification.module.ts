import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { NotOverviewComponent } from "./not-overview/not-overview.component";
import { NotListComponent } from './not-overview/not-list/not-list.component';

@NgModule({
  imports: [
    SharedModule,
  ],
  declarations: [
    NotOverviewComponent,
    NotListComponent
  ],
  exports: [NotOverviewComponent]
})
export class NotificationsModule { }