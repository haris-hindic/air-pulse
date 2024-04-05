import { Component, EventEmitter, Input, Output } from '@angular/core';
import { LoaderService } from '../../shared/services/loader.service';
import { NotificationService } from '../notification.service';
import { NotResponse } from '../model/notification.model';
import { MessageToast } from '../../shared/services/message-toast.service';
import { Router } from '@angular/router';
import { SecurityService } from '../../auth/services/security.service';

@Component({
  selector: 'app-not-overview',
  templateUrl: './not-overview.component.html',
  styleUrls: ['./not-overview.component.css']
})
export class NotOverviewComponent {

  @Input()
  visible!: boolean;
  @Output()
  onModalHidden = new EventEmitter<any>();

  @Output()
  notLoaded = new EventEmitter<number>();

  notifications!: NotResponse[];


  constructor(private notificationService: NotificationService, private router: Router,
    private loader: LoaderService, private messageToast: MessageToast, private securityService: SecurityService) {
  }

  hideModal() {
    this.onModalHidden.emit();
  }

  ngOnInit() {
    this.loadData();
  }

  ngOnChanges() {
    this.loadData();
  }

  loadData() {
    this.loadBookings();
  }

  loadBookings() {
    this.notifications = [];
    this.notificationService.getAll({ 'status': 'Active', 'role': this.securityService.isAdmin() ? 'ADMIN' : 'USER' }).subscribe(
      {
        next: (result) => {
          this.notifications = result;
          this.notLoaded.emit(this.notifications.length);
        }, error: (error) => {
          this.loader.hide();
        }
      }
    );
  }

  redirect(path: string) {
    console.log('path :>> ', path);
    this.router.navigateByUrl(path);
    this.hideModal();
  }

  removeNot(id: number) {
    console.log('id :>> ', id);
    this.notificationService.deactivate(id).subscribe(
      {
        next: (result) => {
          this.messageToast.showSuccess('Successful', result);
          this.loadData();
        }, error: (error) => {
          this.loader.hide();
        }
      }
    );
  }

}
