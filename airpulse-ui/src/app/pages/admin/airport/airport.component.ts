import { Component } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { AirportResponse, AirportRequest } from '../model/airport.model';
import { AirportService } from '../services/airport.service';

@Component({
  selector: 'app-airport',
  templateUrl: './airport.component.html',
  styleUrls: ['./airport.component.css']
})
export class AirportComponent {
  airportDialog: boolean = false;

  airports!: AirportResponse[];

  airport!: AirportResponse;

  selectedAirports!: any | null;

  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private airportService: AirportService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.airports = [];
    this.airportService.getAll().subscribe(
      {
        next: (result) => {
          this.airports = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.airport = new AirportResponse();
    this.airportDialog = true;
  }

  deactivateSelectedProducts() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to deactivate the selected job types?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //BULK DELETE
        this.selectedAirports = null;
        this.messageToast.showSuccess('Successful', 'Aiport deleted.');
        this.findAll();
      }
    });
  }

  editAirport(airport: any) {
    this.airport = { ...airport };
    this.airportDialog = true;
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
    this.airportDialog = false;
  }

  saveAirport(airportRequest: AirportRequest) {

    if (this.airport.id) {
      this.airportService.update(this.airport.id, airportRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Job type updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      })
    } else {
      this.airportService.create(airportRequest).subscribe({
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
