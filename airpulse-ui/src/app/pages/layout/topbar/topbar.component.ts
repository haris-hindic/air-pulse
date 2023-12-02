import { Component } from '@angular/core';
import { SecurityService } from '../../auth/services/security.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {
  showNotificationsModal = false;

  constructor(public securtiyService: SecurityService, private router: Router) {

  }

  toggleNotificationModal(flag: boolean) {
    this.showNotificationsModal = flag;
  }

  logout() {
    this.securtiyService.logout();
    this.router.navigate(['']);
  }
}
