import { Component } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { RouteResponse, RouteRequest } from '../model/route.model';
import { RouteService } from '../services/route.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-route',
  templateUrl: './route.component.html',
  styleUrls: ['./route.component.css']
})
export class RouteComponent {
  routeDialog: boolean = false;

  routes!: RouteResponse[];

  route!: RouteResponse;

  selectedRoutes!: any | null;

  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private routeService: RouteService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.routes = [];
    this.routeService.getAll().subscribe(
      {
        next: (result) => {
          this.routes = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.route = new RouteResponse();
    this.routeDialog = true;
  }

  deactivateSelectedProducts() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to deactivate the selected job types?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //BULK DELETE
        this.selectedRoutes = null;
        this.messageToast.showSuccess('Successful', 'Aiport deleted.');
        this.findAll();
      }
    });
  }

  editRoute(route: any) {
    this.route = { ...route };
    this.routeDialog = true;
  }

  deleteProduct(product: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + product.name + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //ADD DELETE
        //this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Product Deleted', life: 3000 });
        this.messageToast.showSuccess('Successful', 'Job type deleted.');
      }
    });
  }

  hideDialog() {
    this.routeDialog = false;
  }

  saveRoute(routeRequest: RouteRequest) {

    if (this.route.id) {
      this.routeService.update(this.route.id, routeRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Job type updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    } else {
      this.routeService.create(routeRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Job type created.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    }
  }

  handleError(error: any) {
    try {
      this.messageToast.showError(
        error['error']['status'],
        error['error']['errors'] || error['error']['error']
      );
    } catch (err) {
      this.messageToast.showError(
        'Error',
        error
      );
    }
  }

  getSeverity(status: string) {
    switch (status) {
      case 'ACTIVE':
        return 'success';
      case 'INACTIVE':
        return 'danger';
    }

    return 'N/A';
  }
}
