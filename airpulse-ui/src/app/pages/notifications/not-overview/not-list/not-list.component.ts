import { Component, EventEmitter, Input, Output } from '@angular/core';
import { NotResponse } from '../../model/notification.model';

@Component({
  selector: 'app-not-list',
  templateUrl: './not-list.component.html',
  styleUrls: ['./not-list.component.css']
})
export class NotListComponent {

  @Input()
  notifications!: NotResponse[];

  @Output()
  removeNotificationEmitter = new EventEmitter<number>();

  @Output()
  redirectEmitter = new EventEmitter<string>();


  removeNotification(not: NotResponse) {
    console.log('not :>> ', not);
    this.removeNotificationEmitter.emit(not.id);
  }

  redirectPage(path: string) {
    this.redirectEmitter.emit(path);
  }
}
