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
          { label: 'Employee', icon: 'pi pi-fw pi-id-card', routerLink: ['employee'], ...this.itemOptions },
          { label: 'Job Type', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['jobtype'], ...this.itemOptions },
          { label: 'Aircraft', icon: 'pi pi-fw pi-check-square', routerLink: ['aircraft'], ...this.itemOptions },
          { label: 'Airport', icon: 'pi pi-fw pi-box', routerLink: ['airport'], ...this.itemOptions },
          { label: 'Table', icon: 'pi pi-fw pi-table', routerLink: ['/uikit/table'], ...this.itemOptions },
          { label: 'List', icon: 'pi pi-fw pi-list', routerLink: ['/uikit/list'], ...this.itemOptions }]
      }
    ];
  }


}
