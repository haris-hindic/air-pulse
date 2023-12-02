import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageToast } from '../../shared/message-toast.service';
import { LoginRequest } from '../model/login';
import { AuthService } from '../services/auth.service';
import { SecurityService } from '../services/security.service';
import { UserRequest } from '../model/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  date: Date | undefined;


  registerForm!: FormGroup;

  constructor(
    private authService: AuthService,
    private formBuilder: FormBuilder,
    private messageToast: MessageToast,
    private router: Router,
    private securityService: SecurityService
  ) { }
  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      //"Must be a valid phone number (+919367788755,8989829304,786-307-3615)")
      phoneNumber: ['', [Validators.required, Validators.pattern("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")]],
      dateOfBirth: ['', Validators.required],
      email: ['', [Validators.email, Validators.required]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  onSubmit() {
    if (this.registerForm?.valid) {
      const username = this.registerForm.get('username')?.value;
      const password = this.registerForm.get('password')?.value;
      console.log('this.registerForm.value() :>> ', this.registerForm.value as UserRequest);
      this.register(this.registerForm.value as UserRequest);
    }
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
        console.log('error :>> ', error);
        this.messageToast.showError(
          error['error']['status'],
          error['error']['message']
        );
      },
    });
  }
}
