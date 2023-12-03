import { Component, EventEmitter, Input, Output } from '@angular/core';
import { JobTypeRequest, JobTypeResponse } from '../../model/jobtype.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-jobtype-form',
  templateUrl: './jobtype-form.component.html',
  styleUrls: ['./jobtype-form.component.css']
})
export class JobtypeFormComponent {

  @Input()
  visible!: boolean;

  submitted!: boolean;

  @Input()
  jobtype!: JobTypeResponse;

  @Output()
  saveButtonClicked = new EventEmitter<JobTypeRequest>();

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
      title: ['', Validators.required],
      responsibilities: ['', Validators.required],
      status: ['', Validators.required],
      salaryMin: [0, Validators.required],
      salaryMax: [0, Validators.required],
    });
  }

  submitForm() {
    //this.form
  }

  populateFields() {

    if (this.jobtype.id) {
      this.form.get('title')?.patchValue(this.jobtype.title);
      this.form.get('responsibilities')?.patchValue(this.jobtype.responsibilities);
      this.form.get('status')?.patchValue(this.jobtype.status);
      this.form.get('salaryMin')?.patchValue(this.jobtype.salaryMin);
      this.form.get('salaryMax')?.patchValue(this.jobtype.salaryMax);
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
