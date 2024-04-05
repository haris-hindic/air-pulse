import { Component, EventEmitter, Input, Output } from '@angular/core';
import { AbsenceRequest, AbsenceResponse } from '../../model/absence.model';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { EmployeeResponse } from '../../model/employee.model';
import { EmployeeService } from '../../services/employee.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';

@Component({
  selector: 'app-absence-form',
  templateUrl: './absence-form.component.html',
  styleUrls: ['./absence-form.component.css']
})
export class AbsenceFormComponent {

  @Input()
  visible!: boolean;

  @Input()
  absence!: AbsenceResponse;


  @Output()
  saveButtonClicked = new EventEmitter<AbsenceRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;

  statuses!: any[];

  @Input()
  employee!: EmployeeResponse;
  employees!: EmployeeResponse[];
  employeesLov!: any[];

  // startDate!: Date | null;
  // endDate!: Date | null;

  constructor(private formBuilder: FormBuilder, private employeeService: EmployeeService, private messageToast: MessageToast,
    private loader: LoaderService) {
    this.statuses = [
      { label: 'PENDING', value: 'Pending' },
      { label: 'APPROVED', value: 'Approved' },
      { label: 'REJECTED', value: 'Rejected' }
    ];
  }

  dialogStyle: any = { width: '600px' };
  increaseModalSize(height: any) {
    this.dialogStyle = { width: '600px', height: height };
  }

  decreaseModalSize() {
    this.dialogStyle = { width: '600px' };
  }

  ngOnInit() {
    this.loadData();
    this.initForm();
    this.populateFields();
  }

  loadData() {
    this.loadEmployees();
  }

  loadEmployees() {
    this.employees = [];
    this.employeesLov = [];
    if (!this.employee) {
      this.employeeService.getAll().subscribe(
        {
          next: (result) => {
            this.employees = result;
            this.employeesLov = result.map(x => {
              return { label: x.firstName + " " + x.lastName, value: x.id };
            });
          }, error: (error) => {
            this.messageToast.showError("Error", error);
            this.loader.hide();
          }
        }
      );
    } else {
      this.employeesLov.push({ label: this.employee.firstName + " " + this.employee.lastName, value: this.employee.id });
    }
  }

  ngOnChanges() {
    this.populateFields();
  }

  initForm() {
    this.form = this.formBuilder.group({
      type: ['', Validators.required],
      reason: ['', Validators.required],
      status: ['', Validators.required],
      employeeId: [{ value: !this.employee ? '' : this.employee.id, disabled: !!this.employee }, Validators.required,],
      comments: ['', Validators.required],
      dateRange: new FormControl<Date[] | null>(null, Validators.required),
    });
  }

  submitForm() {
    //this.form
  }

  populateFields() {

    if (this.absence.id) {
      this.form.get('type')?.patchValue(this.absence.type);
      this.form.get('reason')?.patchValue(this.absence.reason);
      this.form.get('status')?.patchValue(this.absence.status);
      this.form.get('employeeId')?.patchValue(this.absence.employeeId);
      this.form.get('comments')?.patchValue(this.absence.comments);
      this.form.get('dateRange')?.patchValue([new Date(this.absence.startDate), new Date(this.absence.endDate)]);
      // this.startDate = this.absence.startDate;
      // this.endDate = this.absence.endDate;
    } else {
      this.initForm();
    }
  }


  getSeverity(status: string) {
    switch (status) {
      case 'APPROVED':
        return 'success';
      case 'REJECTED':
        return 'danger';
      case 'PENDING':
        return 'warning';
    }

    return 'N/A';
  }

  populateRequest(): AbsenceRequest {
    const request = new AbsenceRequest();

    request.type = this.form.get('type')?.value;
    request.comments = this.form.get('comments')?.value;
    request.reason = this.form.get('reason')?.value;
    request.employeeId = this.form.get('employeeId')?.value;
    request.startDate = this.form.get('dateRange')?.value[0];
    request.endDate = this.form.get('dateRange')?.value[1];
    request.status = this.form.get('status')?.value;

    return request;
  }

  onSubmit() { this.saveButtonClicked.emit(this.populateRequest()); }
  hideDialog() { this.hideButtonClicked.emit(); }
}
