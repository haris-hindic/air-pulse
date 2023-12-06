import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { AircraftResponse } from '../../model/aircraft.model';
import { StaffResponse, StaffRequest } from '../../model/staff.model';
import { AircraftService } from '../../services/aircraft.service';
import { EmployeeResponse } from '../../model/employee.model';
import { EmployeeService } from '../../services/employee.service';

@Component({
  selector: 'app-staff-form',
  templateUrl: './staff-form.component.html',
  styleUrls: ['./staff-form.component.css']
})
export class StaffFormComponent {
  @Input()
  visible!: boolean;

  @Input()
  staff!: StaffResponse;


  @Output()
  saveButtonClicked = new EventEmitter<StaffRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;

  statuses!: any[];

  @Input()
  aircraft!: AircraftResponse;
  aircrafts!: AircraftResponse[];
  aircraftsLov!: any[];

  employees!: EmployeeResponse[];
  employeeeLov!: any[];


  constructor(private formBuilder: FormBuilder, private aircraftService: AircraftService, private messageToast: MessageToast,
    private loader: LoaderService, private employeeService: EmployeeService) {
    this.statuses = [
      { label: 'ACTIVE', value: 'Active' },
      { label: 'INACTIVE', value: 'Inactive' }
    ];
  }

  ngOnInit() {
    this.loadData();
    this.initForm();
    this.populateFields();
  }

  loadData() {
    this.loadAircrafts();
    this.loadEmployees();
  }

  loadAircrafts() {
    this.aircrafts = [];
    this.aircraftsLov = [];
    if (!this.aircraft) {
      this.aircraftService.getAll().subscribe(
        {
          next: (result) => {
            this.aircrafts = result;
            this.aircraftsLov = result.map(x => {
              return { label: this.aircraft.model + " (" + this.aircraft.manufacturer + ")", value: x.id }
            });
          }, error: (error) => {
            this.messageToast.showError("Error", error);
            this.loader.hide();
          }
        }
      );
    } else {
      this.aircraftsLov.push({ label: this.aircraft.model + " (" + this.aircraft.manufacturer + ")", value: this.aircraft.id })
    }
  }

  loadEmployees() {
    this.employees = [];
    this.employeeeLov = [];
    this.employeeService.getAll({ status: 'Active' }).subscribe(
      {
        next: (result) => {
          this.employees = result;
          this.employeeeLov = result.map(x => {
            return { label: x.firstName + " " + x.lastName, value: x.id }
          });
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  ngOnChanges() {
    this.populateFields();
  }

  initForm() {
    this.form = this.formBuilder.group({
      aircraftId: [{ value: !this.aircraft ? '' : this.aircraft.id, disabled: !!this.aircraft }, Validators.required,],
      employeeId: [0, Validators.required],
      status: ['', Validators.required],
      dateRange: new FormControl<Date[] | null>(null, Validators.required),
    });
  }

  populateFields() {

    if (this.staff.id) {
      this.form.get('employeeId')?.patchValue(this.staff.employeeId);
      this.form.get('aircraftId')?.patchValue(this.staff.aircraftId);
      this.form.get('dateRange')?.patchValue([new Date(this.staff.validFrom), new Date(this.staff.validTo)]);
    } else {
      this.initForm();
    }
  }


  getSeverity(status: string) {
    switch (status) {
      case 'ACTIVE':
        return 'success';
      case 'INACTIVE':
    }

    return 'N/A';
  }

  populateRequest(): StaffRequest {
    const request = new StaffRequest();

    request.employeeId = this.form.get('employeeId')?.value;
    request.aircraftId = this.form.get('aircraftId')?.value;
    request.validFrom = this.form.get('dateRange')?.value[0];
    request.validTo = this.form.get('dateRange')?.value[1];
    const selectedEmployee = this.employees.find(x => x.id === request.employeeId);
    request.summary = selectedEmployee?.firstName + " " + selectedEmployee?.lastName;

    return request;
  }

  onSubmit() { this.saveButtonClicked.emit(this.populateRequest()) }
  hideDialog() { this.hideButtonClicked.emit(); }
}
