import { Component, Input } from '@angular/core';
import { UserResponse } from 'src/app/pages/auth/model/user';

@Component({
  selector: 'app-details-panel',
  templateUrl: './details-panel.component.html',
  styleUrls: ['./details-panel.component.css']
})
export class DetailsPanelComponent {

  @Input()
  user!: UserResponse;

}
