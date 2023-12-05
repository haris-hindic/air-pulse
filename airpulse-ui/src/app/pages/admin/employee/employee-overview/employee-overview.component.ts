import { Component } from '@angular/core';
import { EmployeeService } from '../../services/employee.service';
import { EmployeeRequest, EmployeeResponse } from '../../model/employee.model';
import { ActivatedRoute } from '@angular/router';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';

@Component({
  selector: 'app-employee-overview',
  templateUrl: './employee-overview.component.html',
  styleUrls: ['./employee-overview.component.css']
})
export class EmployeeOverviewComponent {

  employee!: EmployeeResponse;
  date!: Date | null;
  employeeDialog: boolean = false;

  constructor(private employeeService: EmployeeService,
    private route: ActivatedRoute,
    private messageToast: MessageToast) {

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
      },
      error: (err) => {
        this.handleError(err);
      },
    })
  }

  editEmployee() {
    this.employeeDialog = true;
  }

  hideDialog() {
    this.employeeDialog = false;
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
    })
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
