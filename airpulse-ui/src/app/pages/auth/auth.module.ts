import { NgModule } from "@angular/core";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from './register/register.component';
import { CalendarModule } from 'primeng/calendar';
import { SharedModule } from "../shared/shared.module";

@NgModule({
  imports: [
    SharedModule,
    CalendarModule
  ],
  declarations: [LoginComponent, RegisterComponent],
  exports: [LoginComponent]
})
export class AuthModule { }
