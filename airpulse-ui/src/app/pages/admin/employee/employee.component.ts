import { Component } from '@angular/core';
import { EmployeeRequest, EmployeeResponse } from '../model/employee.model';
import { EmployeeService } from '../services/employee.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent {
  employeeDialog: boolean = false;

  employees!: EmployeeResponse[];

  employee!: EmployeeResponse;

  selectedEmployees!: any | null;


  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private employeeService: EmployeeService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.employees = [];
    this.employeeService.getAll().subscribe(
      {
        next: (result) => {
          this.employees = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.employee = new EmployeeResponse();
    this.employeeDialog = true;
  }

  deleteSelectedEmployees() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected employees?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //BULK DELETE
        this.selectedEmployees = null;
        this.messageToast.showSuccess('Successful', 'Employees deleted.');
        this.findAll();
      }
    });
  }

  editEmployee(employee: any) {
    this.employee = { ...employee };
    this.employeeDialog = true;
  }

  deleteEmployee(employee: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete ' + employee.firstName + " " + employee.lastName + '?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        //ADD DELETE
        this.messageToast.showSuccess('Successful', 'Employee deleted.');
      }
    });
  }

  hideDialog() {
    this.employeeDialog = false;
  }

  saveEmployee(employeeRequest: EmployeeRequest) {

    if (this.employee.id) {
      this.employeeService.update(this.employee.id, employeeRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Employee updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      })
    } else {
      this.employeeService.create(employeeRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Employee created.');
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
