import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserRequest, UserResponse } from 'src/app/pages/auth/model/user';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent {
  @Input()
  visible!: boolean;

  @Input()
  user!: UserResponse;

  @Output()
  saveButtonClicked = new EventEmitter<UserRequest>();


  @Output()
  hideButtonClicked = new EventEmitter<any>();


  saveUser(request: UserRequest) { this.saveButtonClicked.emit(request) }
  hideDialog() { this.hideButtonClicked.emit(); }
}
