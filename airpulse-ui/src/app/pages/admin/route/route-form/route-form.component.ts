import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RouteResponse, RouteRequest } from '../../model/route.model';
import { AircraftResponse } from '../../model/aircraft.model';
import { AirportResponse } from '../../model/airport.model';
import { AircraftService } from '../../services/aircraft.service';
import { AirportService } from '../../services/airport.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';

@Component({
  selector: 'app-route-form',
  templateUrl: './route-form.component.html',
  styleUrls: ['./route-form.component.css']
})
export class RouteFormComponent {
  @Input()
  visible!: boolean;

  submitted!: boolean;

  @Input()
  route!: RouteResponse;

  @Output()
  saveButtonClicked = new EventEmitter<RouteRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;


  statuses!: any[];

  aircrafts!: AircraftResponse[];
  aircraftsLov!: any[];

  airports!: AirportResponse[];
  airportsLov!: any[];
  arrivalAirportsLov: any[] = [];

  constructor(private formBuilder: FormBuilder, private aircraftService: AircraftService,
    private airportService: AirportService, private messageToast: MessageToast, private loader: LoaderService) {
    this.submitted = false;
    this.statuses = [
      { label: 'ACTIVE', value: 'Active' },
      { label: 'INACTIVE', value: 'Inactive' }
    ];
  }

  ngOnInit() {
    this.initForm();
    this.populateFields();
    this.loadData();
  }
  loadData() {
    this.loadAircrafts();
    this.loadAirports();
  }

  loadAircrafts() {
    this.aircrafts = [];
    this.aircraftsLov = [];
    this.aircraftService.getAll({ status: 'Active' }).subscribe(
      {
        next: (result) => {
          this.aircrafts = result;
          this.aircraftsLov = result.map(x => {
            return { label: x.model + " (" + x.manufacturer + ")", value: x.id }
          });
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  loadAirports() {
    this.airports = [];
    this.airportsLov = [];
    this.airportService.getAll({ status: 'Active' }).subscribe(
      {
        next: (result) => {
          this.airports = result;
          this.airportsLov = result.map(x => {
            return { label: x.name + " (" + x.iataCode + ")", value: x.id }
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
      aircraftId: ['', Validators.required],
      departureAirportId: ['', Validators.required],
      arrivalAirportId: [{ value: '', disabled: true }, Validators.required],
      distance: [0, Validators.required],
      duration: [0, Validators.required],
      note: ['', Validators.required],
      status: ['', Validators.required],
    });
  }

  onDepartureSelected(event: any): any {
    const selectedAirportId = event.value;

    this.populateAvailableArrivalAirports(selectedAirportId);
  }

  populateAvailableArrivalAirports(departureAirport: number) {
    const airportsWihtoutSelectedForDeparture = this.airports.filter(x => x.id !== departureAirport);
    this.arrivalAirportsLov = [];
    this.arrivalAirportsLov = airportsWihtoutSelectedForDeparture.map(x => {
      return { label: x.name + " (" + x.iataCode + ")", value: x.id }
    });
    this.form.get('arrivalAirportId')?.enable();
  }

  populateFields() {

    if (this.route.id) {
      this.form.get('aircraftId')?.patchValue(this.route.aircraftId);
      this.form.get('arrivalAirportId')?.patchValue(this.route.arrivalAirportId);
      this.form.get('departureAirportId')?.patchValue(this.route.departureAirportId);
      this.form.get('distance')?.patchValue(this.route.distance);
      this.form.get('duration')?.patchValue(this.route.duration);
      this.form.get('note')?.patchValue(this.route.note);
      this.form.get('status')?.patchValue(this.route.status);
      this.populateAvailableArrivalAirports(this.route.departureAirportId);
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

