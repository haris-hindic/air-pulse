import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { MenuConstant } from './menu.constant';

@Component({
  selector: 'app-side-menu',
  templateUrl: './side-menu.component.html',
  styleUrls: ['./side-menu.component.css']
})
export class SideMenuComponent implements OnInit {

  model: MenuItem[] = [];

  constructor() { }

  itemOptions = { styleClass: MenuConstant.MENU_ITEM_CLASS, routerLinkActiveOptions: MenuConstant.ROUTER_LINK_ACTIVE_OPTIONS, routerLinkActive: MenuConstant.ROUTER_LINK_ACTIVE };

  ngOnInit() {
    this.model = [
      { label: 'Admin Panel', styleClass: MenuConstant.MENU_ITEM_CLASS },
      {
        separator: true, items: [
          { label: 'Dashboard', icon: 'pi pi-fw pi-bookmark', routerLink: ['/admin/dashboard'], ...this.itemOptions },
          { label: 'Employees', icon: 'pi pi-fw pi-id-card', routerLink: ['employee'], ...this.itemOptions },
          { label: 'Job Types', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['jobtype'], ...this.itemOptions },
          { label: 'Aircrafts', icon: 'pi pi-fw pi-check-square', routerLink: ['aircraft'], ...this.itemOptions },
          { label: 'Airports', icon: 'pi pi-fw pi-box', routerLink: ['airport'], ...this.itemOptions },
          { label: 'Routes', icon: 'pi pi-fw pi-table', routerLink: ['route'], ...this.itemOptions },
          { label: 'Flights', icon: 'pi pi-fw pi-list', routerLink: ['flight'], ...this.itemOptions }]
      }
    ];
  }


}
