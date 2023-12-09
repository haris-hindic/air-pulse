import { Component, Input } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { AircraftResponse } from '../model/aircraft.model';
import { StaffResponse, StaffRequest } from '../model/staff.model';
import { StaffService } from '../services/staff.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-staff',
  templateUrl: './staff.component.html',
  styleUrls: ['./staff.component.css']
})
export class StaffComponent {
  @Input() aircraft!: AircraftResponse;

  staffDialog: boolean = false;

  staffs!: StaffResponse[];

  staff!: StaffResponse;

  selectedStaff!: any[] | null;


  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private staffService: StaffService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.staffs = [];
    this.staffService.getAll({ aircraftId: this.aircraft.id }).subscribe(
      {
        next: (result) => {
          this.staffs = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.staff = new StaffResponse();
    this.staffDialog = true;
  }

  deleteSelectedStaff() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected staffs?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.staffService.bulkDelete({ ids: this.selectedStaff!.map(x => x.id) }).subscribe({
          next: result => {
            this.selectedStaff = null;
            this.messageToast.showSuccess('Successful', 'Aircraft seats deleted.');
            this.loader.hide();
            this.findAll();
          },
          error: err => {
            this.handleError(err);
          }
        });
      }
    });
  }

  editStaff(staff: any) {
    this.staff = { ...staff };
    this.staffDialog = true;
  }

  deleteStaff(staff: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete selected staff?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.staffService.delete(staff.id).subscribe({
          next: result => {
            this.selectedStaff = null;
            this.messageToast.showSuccess('Successful', 'Staff deleted.');
            this.loader.hide();
            this.findAll();
          },
          error: err => {
            this.handleError(err);
          }
        });
      }
    });
  }

  hideDialog() {
    this.staffDialog = false;
  }

  saveStaff(staffRequest: StaffRequest) {

    if (this.staff.id) {
      this.staffService.update(this.staff.id, staffRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Staff updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    } else {
      this.staffService.create(staffRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Staff created.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    }
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
    }

    return 'N/A';
  }
}
