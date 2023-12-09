import { Component } from '@angular/core';
import { ConfirmationService, MessageService } from 'primeng/api';
import { JobTypeRequest, JobTypeResponse } from '../model/jobtype.model';
import { JobTypeService } from '../services/jobtype.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { LoaderService } from '../../shared/services/loader.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-jobtype',
  templateUrl: './jobtype.component.html',
  styleUrls: ['./jobtype.component.css']
})
export class JobtypeComponent {

  jobtypeDialog: boolean = false;

  jobtypes!: JobTypeResponse[];

  jobtype!: JobTypeResponse;

  selectedJobTypes!: any | null;

  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private jobtypeService: JobTypeService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.jobtypes = [];
    this.jobtypeService.getAll().subscribe(
      {
        next: (result) => {
          this.jobtypes = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.jobtype = new JobTypeResponse();
    this.jobtypeDialog = true;
  }

  deactivateSelectedProducts() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to deactivate the selected job types?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //BULK DELETE
        this.selectedJobTypes = null;
        this.messageToast.showSuccess('Successful', 'Job type deleted.');
        this.findAll();
      }
    });
  }

  editJobtype(jobtype: any) {
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
  }

  saveJobtype(jobTypeRequest: JobTypeRequest) {

    if (this.jobtype.id) {
      this.jobtypeService.update(this.jobtype.id, jobTypeRequest).subscribe({
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
      this.jobtypeService.create(jobTypeRequest).subscribe({
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
