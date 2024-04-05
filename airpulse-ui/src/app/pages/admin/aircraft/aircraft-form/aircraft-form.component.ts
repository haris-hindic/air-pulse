import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { AircraftResponse, AircraftRequest } from '../../model/aircraft.model';

@Component({
  selector: 'app-aircraft-form',
  templateUrl: './aircraft-form.component.html',
  styleUrls: ['./aircraft-form.component.css']
})
export class AircraftFormComponent {

  @Input()
  visible!: boolean;

  @Input()
  aircraft!: AircraftResponse;

  @Output()
  saveButtonClicked = new EventEmitter<AircraftRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;

  statuses!: any[];

  date!: Date | null;

  constructor(private formBuilder: FormBuilder) {
    this.statuses = [
      { label: 'ACTIVE', value: 'Active' },
      { label: 'IN SERVICE', value: 'In Service' },
      { label: 'INACTIVE', value: 'Inactive' }
    ];
  }

  ngOnInit() {
    this.initForm();
    this.populateFields();
    this.decreaseModalSize();
  }

  ngOnChanges() {
    this.populateFields();
    this.decreaseModalSize();
  }

  dialogStyle: any = { width: '600px' };
  increaseModalSize(height: any) {
    this.dialogStyle = { width: '600px', height: height };
  }

  decreaseModalSize() {
    this.dialogStyle = { width: '600px' };
  }

  initForm() {
    this.form = this.formBuilder.group({
      model: ['', Validators.required],
      status: ['', Validators.required],
      manufacturer: ['', Validators.required],
      buildDate: ['', Validators.required],
      averageSpeed: ['', Validators.required]
    });
  }

  populateFields() {

    if (this.aircraft.id) {
      this.form.get('model')?.patchValue(this.aircraft.model);
      this.form.get('status')?.patchValue(this.aircraft.status);
      this.form.get('manufacturer')?.patchValue(this.aircraft.manufacturer);
      this.form.get('averageSpeed')?.patchValue(this.aircraft.averageSpeed);
      this.form.get('buildDate')?.patchValue(new Date(this.aircraft.buildDate));
      this.date = this.aircraft.buildDate;
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
      case 'IN SERVICE':
        return 'warning';
    }

    return 'N/A';
  }

  onSubmit() { this.saveButtonClicked.emit(this.form.value); }
  hideDialog() { this.hideButtonClicked.emit(); }
}
