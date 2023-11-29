import { NgModule } from "@angular/core";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from './register/register.component';
import { CalendarModule } from 'primeng/calendar';
import { SharedModule } from "../shared/shared.module";
import { RouterModule } from "@angular/router";

@NgModule({
  imports: [
    SharedModule,
    CalendarModule,
    RouterModule.forChild([
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      { path: '**', redirectTo: '/notfound' }
    ])
  ],
  declarations: [LoginComponent, RegisterComponent],
  exports: []
})
export class AuthModule { }
