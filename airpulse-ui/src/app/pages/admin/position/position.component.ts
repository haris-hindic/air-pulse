import { Component, Input } from '@angular/core';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { EmployeeResponse } from '../model/employee.model';
import { PositionRequest, PositionResponse } from '../model/position.model';
import { PositionService } from '../services/position.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-position',
  templateUrl: './position.component.html',
  styleUrls: ['./position.component.css']
})
export class PositionComponent {
  @Input() employee!: EmployeeResponse;

  positionDialog: boolean = false;

  positions!: PositionResponse[];

  position!: PositionResponse;

  selectedpositions!: any[] | null;


  constructor(private messageToast: MessageToast, private confirmationService: ConfirmationService,
    private positionService: PositionService, private loader: LoaderService) { }

  ngOnInit() {
    this.findAll();
  }

  findAll() {
    this.positions = [];
    this.positionService.getByEmployeeId(this.employee.id).subscribe(
      {
        next: (result) => {
          this.positions = result;
        }, error: (error) => {
          this.messageToast.showError("Error", error);
          this.loader.hide();
        }
      }
    );
  }

  openNew() {
    this.position = new PositionResponse();
    this.positionDialog = true;
  }

  deleteSelectedPositions() {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete the selected positions?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.positionService.bulkDelete({ ids: this.selectedpositions!.map(x => x.id) }).subscribe({
          next: result => {
            this.selectedpositions = null;
            this.messageToast.showSuccess('Successful', 'Positions deleted.');
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

  editPosition(position: any) {
    this.position = { ...position };
    this.positionDialog = true;
  }

  deletePosition(position: any) {
    this.confirmationService.confirm({
      message: 'Are you sure you want to delete selected absece?',
      header: 'Confirm',
      icon: 'pi pi-exclamation-triangle',
      accept: () => {
        this.positionService.delete(position.id).subscribe({
          next: result => {
            this.messageToast.showSuccess('Successful', 'Position deleted.');
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
    this.positionDialog = false;
  }

  savePosition(positionRequest: PositionRequest) {

    if (this.position.id) {
      this.positionService.update(this.position.id, positionRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Position updated.');
          this.hideDialog();
        },
        error: (error) => {
          this.handleError(error);
        }
      });
    } else {
      this.positionService.create(positionRequest).subscribe({
        next: () => {
          this.findAll();
          this.messageToast.showSuccess('Successful', 'Position created.');
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
    this.loader.hide();
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
