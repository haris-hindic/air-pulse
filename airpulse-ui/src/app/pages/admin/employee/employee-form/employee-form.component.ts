import { Component, EventEmitter, Input, Output } from '@angular/core';
import { EmployeeRequest, EmployeeResponse } from '../../model/employee.model';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-employee-form',
  templateUrl: './employee-form.component.html',
  styleUrls: ['./employee-form.component.css']
})
export class EmployeeFormComponent {

  @Input()
  visible!: boolean;

  @Input()
  employee!: EmployeeResponse;

  @Output()
  saveButtonClicked = new EventEmitter<EmployeeRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;

  statuses!: any[];

  date!: Date | null;

  constructor(private formBuilder: FormBuilder) {
    this.statuses = [
      { label: 'ACTIVE', value: 'Active' },
      { label: 'INACTIVE', value: 'Inactive' }
    ];
  }

  ngOnInit() {
    this.initForm();
    this.populateFields();
  }

  ngOnChanges() {
    this.populateFields();
  }

  initForm() {
    this.form = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      gender: ['', Validators.required],
      phoneNumber: ['', [Validators.required, Validators.pattern("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$")]],
      dateOfBirth: new FormControl<Date | null>(null, Validators.required),
    });
  }

  submitForm() {
    //this.form
  }

  populateFields() {

    if (this.employee.id) {
      this.form.get('firstName')?.patchValue(this.employee.firstName);
      this.form.get('lastName')?.patchValue(this.employee.lastName);
      this.form.get('email')?.patchValue(this.employee.email);
      this.form.get('gender')?.patchValue(this.employee.gender);
      this.form.get('phoneNumber')?.patchValue(this.employee.phoneNumber);
      this.form.get('dateOfBirth')?.patchValue(new Date(this.employee.dateOfBirth));
      this.date = this.employee.dateOfBirth;
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

  onSubmit() { this.saveButtonClicked.emit(this.form.value) }
  hideDialog() { this.hideButtonClicked.emit(); }
}
