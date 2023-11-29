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
          { label: 'Dashboard', icon: 'pi pi-fw pi-bookmark', routerLink: ['/admin'], ...this.itemOptions },
          { label: 'Employee', icon: 'pi pi-fw pi-id-card', routerLink: ['employee'], ...this.itemOptions },
          { label: 'Aircraft', icon: 'pi pi-fw pi-check-square', routerLink: ['aircraft'], ...this.itemOptions },
          { label: 'Invalid State', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['/uikit/invalidstate'], ...this.itemOptions },
          { label: 'Button', icon: 'pi pi-fw pi-box', routerLink: ['/uikit/button'], ...this.itemOptions },
          { label: 'Table', icon: 'pi pi-fw pi-table', routerLink: ['/uikit/table'], ...this.itemOptions },
          { label: 'List', icon: 'pi pi-fw pi-list', routerLink: ['/uikit/list'], ...this.itemOptions }]
      }
    ];
  }


}
