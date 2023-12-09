import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { AircraftResponse } from '../../model/aircraft.model';
import { AircraftseatResponse, AircraftseatRequest } from '../../model/aircraftseat.model';
import { AircraftService } from '../../services/aircraft.service';

@Component({
  selector: 'app-aircraftseat-form',
  templateUrl: './aircraftseat-form.component.html',
  styleUrls: ['./aircraftseat-form.component.css']
})
export class AircraftseatFormComponent {
  @Input()
  visible!: boolean;

  @Input()
  aircraftseat!: AircraftseatResponse;


  @Output()
  saveButtonClicked = new EventEmitter<AircraftseatRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;

  @Input()
  seatClasses!: any[];

  @Input()
  aircraft!: AircraftResponse;
  aircrafts!: AircraftResponse[];
  aircraftsLov!: any[];


  constructor(private formBuilder: FormBuilder, private aircraftService: AircraftService, private messageToast: MessageToast,
    private loader: LoaderService) {
  }

  ngOnInit() {
    this.loadData();
    this.initForm();
    this.populateFields();
  }

  loadData() {
    this.loadAircrafts();
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
              return { label: this.aircraft.model + " (" + this.aircraft.manufacturer + ")", value: x.id };
            });
          }, error: (error) => {
            this.messageToast.showError("Error", error);
            this.loader.hide();
          }
        }
      );
    } else {
      this.aircraftsLov.push({ label: this.aircraft.model + " (" + this.aircraft.manufacturer + ")", value: this.aircraft.id });
    }
  }

  ngOnChanges() {
    this.populateFields();
  }

  initForm() {
    this.form = this.formBuilder.group({
      quantity: [0, Validators.required],
      priceModifier: [0, Validators.required],
      seatClass: ['', Validators.required],
      aircraftId: [{ value: !this.aircraft ? '' : this.aircraft.id, disabled: !!this.aircraft }, Validators.required,],
    });
  }

  submitForm() {
    //this.form
  }

  populateFields() {

    if (this.aircraftseat.id) {
      this.form.get('quantity')?.patchValue(this.aircraftseat.quantity);
      this.form.get('priceModifier')?.patchValue(this.aircraftseat.priceModifier);
      this.form.get('seatClass')?.patchValue(this.aircraftseat.seatClass);
      this.form.get('aircraftId')?.patchValue(this.aircraftseat.aircraftId);
    } else {
      this.initForm();
    }
  }


  getSeverity(status: string) {
    switch (status) {
      case 'FIRST CLASS':
        return 'success';
      case 'BUSINESS':
        return 'info';
      case 'PREMIUM ECONOMY':
        return 'warning';
      case 'ECONOMY':
        return 'danger';
    }

    return 'N/A';
  }

  populateRequest(): AircraftseatRequest {
    const request = new AircraftseatRequest();

    request.seatClass = this.form.get('seatClass')?.value;
    request.priceModifier = (this.form.get('priceModifier')?.value / 100) + 1;
    request.aircraftId = this.form.get('aircraftId')?.value;
    request.quantity = this.form.get('quantity')?.value;

    return request;
  }

  onSubmit() { this.saveButtonClicked.emit(this.populateRequest()); }
  hideDialog() { this.hideButtonClicked.emit(); }
}
