import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { TopbarComponent } from "./topbar/topbar.component";
import { FooterComponent } from './footer/footer.component';
import { NotificationsModule } from "../notifications/notification.module";

@NgModule({
    declarations: [TopbarComponent, FooterComponent],
    exports: [TopbarComponent, FooterComponent],
    imports: [
        SharedModule,
        NotificationsModule
    ]
})
export class LayoutModule { }