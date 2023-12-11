import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { UserRequest, UserResponse } from '../../model/user';
import { AuthService } from '../../services/auth.service';
import { SecurityService } from '../../services/security.service';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent {
  date: Date | undefined;


  registerForm!: FormGroup;

  @Output() formSubmitted = new EventEmitter<UserRequest>();

  @Input() user!: UserResponse;

  constructor(
    private formBuilder: FormBuilder
  ) { }


  ngOnInit() {
    this.initForm();
    this.populateFields();
  }

  initForm() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      username: [{ value: '', disabled: !!this.user }, Validators.required],
      //"Must be a valid phone number (+919367788755,8989829304,786-307-3615)")
      phoneNumber: ['', [Validators.required, Validators.pattern("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")]],
      dateOfBirth: new FormControl<Date | null>(null, Validators.required),
      email: ['', [Validators.email, Validators.required]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  populateFields() {

    if (this.user && this.user.id) {
      this.registerForm.get('firstName')?.patchValue(this.user.firstName);
      this.registerForm.get('lastName')?.patchValue(this.user.lastName);
      this.registerForm.get('email')?.patchValue(this.user.email);
      this.registerForm.get('username')?.patchValue(this.user.username);
      this.registerForm.get('phoneNumber')?.patchValue(this.user.phoneNumber);
      this.registerForm.get('dateOfBirth')?.patchValue(new Date(this.user.dateOfBirth));
      this.date = this.user.dateOfBirth;
    } else {
      this.initForm();
    }
  }

  onSubmit() {
    if (this.registerForm?.valid) {
      this.formSubmitted.emit(this.registerForm.value as UserRequest);
    }
  }
}
