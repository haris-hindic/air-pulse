import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AirportResponse, AirportRequest } from '../../model/airport.model';

@Component({
  selector: 'app-airport-form',
  templateUrl: './airport-form.component.html',
  styleUrls: ['./airport-form.component.css']
})
export class AirportFormComponent {
  @Input()
  visible!: boolean;

  submitted!: boolean;

  @Input()
  airport!: AirportResponse;

  @Output()
  saveButtonClicked = new EventEmitter<AirportRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;


  statuses!: any[];

  constructor(private formBuilder: FormBuilder) {
    this.submitted = false;
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
      city: ['', Validators.required],
      country: ['', Validators.required],
      iataCode: ['', Validators.required],
      icaoCode: ['', Validators.required],
      name: ['', Validators.required],
    });
  }

  submitForm() {
    //this.form
  }

  populateFields() {

    if (this.airport.id) {
      this.form.get('city')?.patchValue(this.airport.city);
      this.form.get('country')?.patchValue(this.airport.country);
      this.form.get('iataCode')?.patchValue(this.airport.iataCode);
      this.form.get('icaoCode')?.patchValue(this.airport.icaoCode);
      this.form.get('name')?.patchValue(this.airport.name);
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

  onSubmit() { this.submitted = true; this.saveButtonClicked.emit(this.form.value) }
  hideDialog() { this.hideButtonClicked.emit(); }
}
