import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MessageToast } from 'src/app/pages/shared/services/message-toast.service';
import { AircraftResponse, AircraftRequest } from '../../model/aircraft.model';
import { AircraftService } from '../../services/aircraft.service';

@Component({
  selector: 'app-aircraft-overview',
  templateUrl: './aircraft-overview.component.html',
  styleUrls: ['./aircraft-overview.component.css']
})
export class AircraftOverviewComponent {
  aircraft!: AircraftResponse;
  date!: Date | null;
  created!: string | null;
  modified!: string | null;
  aircraftDialog: boolean = false;
  statuses: { label: string; value: string; }[];
  imageDialog: boolean = false;

  constructor(private aircraftService: AircraftService,
    private route: ActivatedRoute,
    private messageToast: MessageToast) {
    this.statuses = [];
  }

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    const aircraftId = this.route.snapshot.paramMap.get('id') as unknown as number;
    this.aircraftService.getById(aircraftId).subscribe({
      next: result => {
        this.aircraft = result;
        this.statuses.push({ label: result.status.toUpperCase(), value: result.status });
        this.date = new Date(this.aircraft.buildDate);
        this.created = new Date(this.aircraft.created).toUTCString() + " (" + this.aircraft.createdBy + ")";
        this.modified = this.aircraft.modified ? new Date(this.aircraft.modified).toUTCString() + " (" + this.aircraft.modifiedBy + ")" : '';
      },
      error: (err) => {
        this.handleError(err);
      },
    });
  }

  editAircraft() {
    this.aircraftDialog = true;
  }

  hideDialog() {
    this.aircraftDialog = false;
  }

  hideImageDialog() {
    this.imageDialog = false;
  }

  changeImage() {
    this.imageDialog = true;
  }

  saveAircraft(aircraftRequest: AircraftRequest) {

    this.aircraftService.update(this.aircraft.id, aircraftRequest).subscribe({
      next: () => {
        this.loadData();
        this.messageToast.showSuccess('Successful', 'aircraft updated.');
        this.hideDialog();
      },
      error: (error) => {
        this.handleError(error);
      }
    });
  }

  saveImage(image: any) {
    const aircraftRequest = AircraftRequest.createFromResponse(this.aircraft);
    aircraftRequest.imageData = image;
    this.hideImageDialog();
    this.saveAircraft(aircraftRequest);
  }

  handleError(error: any) {
    try {
      this.messageToast.showError(
        error['error']['status'],
        error['error']['errors'] || error['error']['error']
      );
    } catch (err) {
      this.messageToast.showError(
        'Error',
        error
      );
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
}
