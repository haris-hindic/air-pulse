import { NgModule } from "@angular/core";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from './register/register.component';
import { SharedModule } from "../shared/shared.module";
import { RouterModule } from "@angular/router";
import { RegisterFormComponent } from './register/register-form/register-form.component';

@NgModule({
  imports: [
    SharedModule,
    RouterModule.forChild([
      { path: 'login', component: LoginComponent },
      { path: 'register', component: RegisterComponent },
      //{ path: '**', redirectTo: '/notfound' }
    ])
  ],
  declarations: [LoginComponent, RegisterComponent, RegisterFormComponent],
  exports: [RegisterFormComponent]
})
export class AuthModule { }
