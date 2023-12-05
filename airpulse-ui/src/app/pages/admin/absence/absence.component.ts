import { Component, Input } from '@angular/core';
import { AbsenceRequest, AbsenceResponse } from '../model/absence.model';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { AbsenceService } from '../services/absence.service';
import { EmployeeResponse } from '../model/employee.model';

@Component({
  selector: 'app-absence',
  templateUrl: './absence.component.html',
  styleUrls: ['./absence.component.css']
})
export class AbsenceComponent {

  @Input() employee!: EmployeeResponse;

  absenceDialog: boolean = false;

  absences!: AbsenceResponse[];

  absence!: AbsenceResponse;

  selectedAbsences!: any[] | null;


  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private absenceService: AbsenceService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.absences = [];
    this.absenceService.getByEmployeeId(this.employee.id).subscribe(
      {
        next: (result) => {
          this.absences = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.absence = new AbsenceResponse();
    this.absenceDialog = true;
  }

  deleteSelectedAbsences() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected absences?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.absenceService.bulkDelete({ ids: this.selectedAbsences!.map(x => x.id) }).subscribe({
          next: result => {
            this.selectedAbsences = null;
            this.messageToast.showSuccess('Successful', 'Absences deleted.');
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

  editAbsence(absence: any) {
    this.absence = { ...absence };
    this.absenceDialog = true;
  }

  deleteAbsence(absence: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete selected absece?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.absenceService.delete(absence.id).subscribe({
          next: result => {
            this.selectedAbsences = null;
            this.messageToast.showSuccess('Successful', 'Absence deleted.');
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
    this.absenceDialog = false;
  }

  saveAbsence(absenceRequest: AbsenceRequest) {

    if (this.absence.id) {
      this.absenceService.update(this.absence.id, absenceRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Absence updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      })
    } else {
      this.absenceService.create(absenceRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Absence created.');
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
      case 'APPROVED':
        return 'success';
      case 'REJECTED':
        return 'danger';
      case 'PENDING':
        return 'warning';
    }

    return 'N/A';
  }
}
