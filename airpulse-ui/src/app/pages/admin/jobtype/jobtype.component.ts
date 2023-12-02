import { Component } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { JobTypeResponse } from '../model/jobtype.model';
import { JobTypeService } from '../services/jobtype.service';
import { MessageToast } from '../../shared/message-toast.service';

@Component({
  selector: 'app-jobtype',
  templateUrl: './jobtype.component.html',
  styleUrls: ['./jobtype.component.css']
})
export class JobtypeComponent {

  jobtypeDialog: boolean = false;

  jobtypes!: JobTypeResponse[];

  jobtype!: JobTypeResponse;

  selectedJobTypes!: any | null;

  submitted: boolean = false;

  statuses!: any[];

  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private jobtypeService: JobTypeService) { }

  ngOnInit() {
    //find job types
    this.findAll();

    this.statuses = [
      { label: 'ACTIVE', value: 'Active' },
      { label: 'INACTIVE', value: 'Inactive' }
    ];
  }

  findAll() {
    this.jobtypeService.getAll().subscribe(
      {
        next: (result) => {
          this.jobtypes = result;
        }, error: (error) => {
          console.log('error :>> ', error);
          // this.messageToast.showError(
          //   error['error']['status'],
          //   error['error']['message']
          // );
        }
      }
    );
  }

  openNew() {
    //this.product = {};
    this.submitted = false;
    this.jobtypeDialog = true;
  }

  deleteSelectedProducts() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected products?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //BULK DELETE
        this.selectedJobTypes = null;
        //this.messageToast.add({ severity: 'success', summary: 'Successful', detail: 'Products Deleted', life: 3000 });
        this.messageToast.showSuccess('Successful', 'Job type deleted.');
      }
    });
  }

  editProduct(jobtype: any) {
    this.jobtype = { ...jobtype };
    this.jobtypeDialog = true;
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
    this.jobtypeDialog = false;
    this.submitted = false;
  }

  saveProduct() {
    this.submitted = true;

    if (this.jobtype.id) {

      //this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Job type Updated', life: 3000 });
      this.messageToast.showSuccess('Successful', 'Job type updated.');
    } else {

      //this.messageService.add({ severity: 'success', summary: 'Successful', detail: 'Job type Created', life: 3000 });
      this.messageToast.showSuccess('Successful', 'Job type created.');
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
