import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageToast } from '../../shared/services/message-toast.service';
import { AuthService } from '../services/auth.service';
import { SecurityService } from '../services/security.service';
import { UserRequest } from '../model/user';
import { LoaderService } from '../../shared/services/loader.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(
    private authService: AuthService,
    private messageToast: MessageToast,
    private router: Router,
    private securityService: SecurityService,
    private loader: LoaderService
  ) { }
  ngOnInit() {
  }

  register(UserRequest: UserRequest) {
    this.authService.register(UserRequest).subscribe({
      next: (result) => {
        this.securityService.saveToken(result);
        this.messageToast.showSuccess(
          'Success',
          `Welcome ${this.securityService.getFieldFromJWT("username")}!`
        );
        this.router.navigate(['']);
      },
      error: (error) => {
        this.messageToast.showError(
          error['error']['status'],
          error['error']['message']
        );
        this.loader.hide();
      },
    });
  }
}
