import { Component } from '@angular/core';
import { LoginRequest } from '../model/login';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { SecurityService } from '../services/security.service';
import { LoaderService } from '../../shared/services/loader.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  loginForm!: FormGroup;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private messageToast: MessageToast,
    private router: Router,
    private securityService: SecurityService,
    private loader: LoaderService
  ) { }
  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.loginForm?.valid) {
      const username = this.loginForm.get('username')?.value;
      const password = this.loginForm.get('password')?.value;
      this.login(username, password);
    }
  }

  login(username: string, password: string) {
    this.authService.login(new LoginRequest(username, password)).subscribe({
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
