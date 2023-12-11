import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserResponse } from 'src/app/pages/auth/model/user';

@Component({
  selector: 'app-details-card',
  templateUrl: './details-card.component.html',
  styleUrls: ['./details-card.component.css']
})
export class DetailsCardComponent {

  @Input()
  user!: UserResponse;

  @Output()
  changeImageButtonClicked = new EventEmitter<any>();

  @Output()
  editUserButtonClicked = new EventEmitter<any>();

  editUser() {
    this.editUserButtonClicked.emit();
  }

  changeImage() {
    this.changeImageButtonClicked.emit();
  }
}
