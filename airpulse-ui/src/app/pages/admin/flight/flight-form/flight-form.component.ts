import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouteResponse } from '../../model/route.model';
import { RouteService } from '../../services/route.service';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { LoaderService } from 'src/app/pages/shared/services/loader.service';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { FlightResponse, FlightRequest } from '../../model/flight.model';

@Component({
  selector: 'app-flight-form',
  templateUrl: './flight-form.component.html',
  styleUrls: ['./flight-form.component.css']
})
export class FlightFormComponent {

  @Input()
  visible!: boolean;

  @Input()
  flight!: FlightResponse;


  @Output()
  saveButtonClicked = new EventEmitter<FlightRequest>();

  @Output()
  hideButtonClicked = new EventEmitter<any>();

  form!: FormGroup;

  statuses!: any[];

  routes!: RouteResponse[];
  routesLov!: any[];

  constructor(private formBuilder: FormBuilder, private routeService: RouteService, private messageToast: MessageToast,
    private loader: LoaderService) {
  }

  ngOnInit() {
    this.loadData();
    this.initForm();
    this.populateFields();
  }

  loadData() {
    this.loadRoutes();
  }


  loadRoutes() {
    this.routes = [];
    this.routesLov = [];

    this.routeService.getAll().subscribe(
      {
        next: (result) => {
          this.routes = result;
          this.routesLov = result.map(x => {
            return { label: x.departureAirportDetails + "-" + x.arrivalAirportDetails, value: x.id }
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
      basePrice: [1, Validators.required],
      routeId: ['', Validators.required,],
      dateRange: new FormControl<Date[] | null>(null, Validators.required),
    });
  }

  submitForm() {
    //this.form
  }

  populateFields() {

    if (this.flight.id) {
      this.form.get('routeId')?.patchValue(this.flight.routeId);
      this.form.get('basePrice')?.patchValue(this.flight.basePrice);
      this.form.get('dateRange')?.patchValue([new Date(this.flight.departure), new Date(this.flight.arrival)]);
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

  populateRequest(): FlightRequest {
    const request = new FlightRequest();

    request.routeId = this.form.get('routeeId')?.value;
    request.departure = this.form.get('dateRange')?.value[0];
    request.arrival = this.form.get('dateRange')?.value[1];
    request.basePrice = this.form.get('basePrice')?.value;

    return request;
  }

  onSubmit() { this.saveButtonClicked.emit(this.populateRequest()) }
  hideDialog() { this.hideButtonClicked.emit(); }
}
