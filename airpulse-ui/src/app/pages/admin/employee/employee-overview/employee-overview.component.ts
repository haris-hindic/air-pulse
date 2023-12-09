import { Component } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { EmployeeRequest, EmployeeResponse } from '../../model/employee.model';
import { ActivatedRoute } from '@angular/router';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-employee-overview',
  templateUrl: './employee-overview.component.html',
  styleUrls: ['./employee-overview.component.css']
})
export class EmployeeOverviewComponent {

  employee!: EmployeeResponse;
  date!: Date | null;
  employeeDialog: boolean = false;
  imageDialog: boolean = false;

  created!: string | null;
  modified!: string | null;

  constructor(private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private messageToast: MessageToast,
    private confirmationService: ConfirmationService,
    private loader: LoaderService) {

  }

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    const emplyoeeId = this.route.snapshot.paramMap.get('id') as unknown as number;
    this.employeeService.getById(emplyoeeId).subscribe({
      next: result => {
        this.employee = result;
        this.date = new Date(this.employee.dateOfBirth);
        this.created = new Date(this.employee.created).toUTCString() + " (" + this.employee.createdBy + ")";
        this.modified = this.employee.modified ? new Date(this.employee.modified).toUTCString() + " (" + this.employee.modifiedBy + ")" : '';
      },
      error: (err) => {
        this.handleError(err);
      },
    });
  }

  editEmployee() {
    this.employeeDialog = true;
  }

  hideDialog() {
    this.employeeDialog = false;
  }

  hideImageDialog() {
    this.imageDialog = false;
  }

  changeImage() {
    this.imageDialog = true;
  }

  saveEmployee(employeeRequest: EmployeeRequest) {

    this.employeeService.update(this.employee.id, employeeRequest).subscribe({
      next: () => {
        this.loadData();
        this.messageToast.showSuccess('Successful', 'Employee updated.');
        this.hideDialog();
      },
      error: (error) => {
        this.handleError(error);
      }
    });
  }

  deleteEmployee() {
    console.log('object :>> ', this.confirmationService);
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + this.employee.firstName + " " + this.employee.lastName + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.employeeService.delete(this.employee.id).subscribe({
          next: result => {
            this.messageToast.showSuccess('Successful', 'Employee deleted.');
            this.loader.hide();
            this.loadData();
          },
          error: err => {
            this.handleError(err);
          }
        });
      }
    });
  }

  saveImage(image: any) {
    const employeeRequest = EmployeeRequest.createFromResponse(this.employee);
    employeeRequest.imageData = image;
    this.hideImageDialog();
    this.saveEmployee(employeeRequest);
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
}
