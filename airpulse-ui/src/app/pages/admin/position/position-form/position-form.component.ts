import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { EmployeeResponse } from '../../model/employee.model';
import { PositionResponse, PositionRequest } from '../../model/position.model';
import { EmployeeService } from '../../services/employee.service';
import { JobTypeService } from '../../services/jobtype.service';

@Component({
  selector: 'app-position-form',
  templateUrl: './position-form.component.html',
  styleUrls: ['./position-form.component.css']
})
export class PositionFormComponent {

  @Input()
  visible!: boolean;

  @Input()
  position!: PositionResponse;


  @Output()
  saveButtonClicked = new EventEmitter<PositionRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;

  statuses!: any[];

  @Input()
  employee!: EmployeeResponse;
  employees!: EmployeeResponse[];
  employeesLov!: any[];
  jobTypesLov!: any[];

  // startDate!: Date | null;
  // endDate!: Date | null;

  constructor(private formBuilder: FormBuilder, private employeeService: EmployeeService, private messageToast: MessageToast,
    private loader: LoaderService, private jobtypeService: JobTypeService) {
  }

  ngOnInit() {
    this.loadData();
    this.initForm();
    this.populateFields();
  }

  loadData() {
    this.loadEmployees();
    this.loadJobtypes();
  }

  loadJobtypes() {
    this.jobTypesLov = [];
    this.jobtypeService.getAll().subscribe(
      {
        next: (result) => {
          this.jobTypesLov = result.map(x => {
            return { label: x.title, value: x.id }
          });
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
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
              return { label: x.firstName + " " + x.lastName, value: x.id }
            });
          }, error: (error) => {
            this.messageToast.showError("Error", error);
            this.loader.hide();
          }
        }
      );
    } else {
      this.employeesLov.push({ label: this.employee.firstName + " " + this.employee.lastName, value: this.employee.id })
    }
  }

  ngOnChanges() {
    this.populateFields();
  }

  initForm() {
    this.form = this.formBuilder.group({
      jobTypeId: ['', Validators.required],
      salary: [1, Validators.required],
      employeeId: [{ value: !this.employee ? '' : this.employee.id, disabled: !!this.employee }, Validators.required,],
      dateRange: new FormControl<Date[] | null>(null, Validators.required),
    });
  }

  submitForm() {
    //this.form
  }

  populateFields() {

    if (this.position.id) {
      this.form.get('jobTypeId')?.patchValue(this.position.jobTypeId);
      this.form.get('employeeId')?.patchValue(this.position.employeeId);
      this.form.get('salary')?.patchValue(this.position.salary);
      this.form.get('dateRange')?.patchValue([new Date(this.position.startDate), new Date(this.position.endDate)]);
      // this.startDate = this.position.startDate;
      // this.endDate = this.position.endDate;
    } else {
      this.initForm();
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

  populateRequest(): PositionRequest {
    const request = new PositionRequest();

    request.jobTypeId = this.form.get('jobTypeId')?.value;
    request.employeeId = this.form.get('employeeId')?.value;
    request.startDate = this.form.get('dateRange')?.value[0];
    request.endDate = this.form.get('dateRange')?.value[1];
    request.salary = this.form.get('salary')?.value;

    return request;
  }

  onSubmit() { this.saveButtonClicked.emit(this.populateRequest()) }
  hideDialog() { this.hideButtonClicked.emit(); }
}
