import { Component } from '@angular/core';
import { UserRequest, UserResponse } from '../../auth/model/user';
import { ActivatedRoute } from '@angular/router';
import { ConfirmationService } from 'primeng/api';
import { LoaderService } from '../../shared/services/loader.service';
import { MessageToast } from '../../shared/services/message-toast.service';
import { UserService } from '../services/user.service';
import { SecurityService } from '../../auth/services/security.service';

@Component({
  providers: [ConfirmationService],
  selector: 'app-useradmin',
  templateUrl: './useradmin.component.html',
  styleUrls: ['./useradmin.component.css']
})
export class UseradminComponent {

  user!: UserResponse;

  imageDialog: boolean = false; dialog: boolean = false;

  constructor(private userService: UserService,
    private route: ActivatedRoute,
    private messageToast: MessageToast,
    private confirmationService: ConfirmationService,
    private loader: LoaderService,
    private securityService: SecurityService) {

  }

  ngOnInit() {
    this.loadData();
  }

  loadData() {
    const userId = this.securityService.getFieldFromJWT('id') as number;
    this.userService.getById(userId).subscribe({
      next: result => {
        this.user = result;
        // this.date = new Date(this.user.dateOfBirth);
        // this.created = new Date(this.user.created).toUTCString() + " (" + this.user.createdBy + ")";
        // this.modified = this.user.modified ? new Date(this.user.modified).toUTCString() + " (" + this.user.modifiedBy + ")" : '';
      },
      error: (err) => {
        this.handleError(err);
      },
    });
  }


  hideImageDialog() {
    this.imageDialog = false;
  }

  changeImage() {
    this.imageDialog = true;
  }

  hideDialog() {
    this.dialog = false;
  }

  editUser() {
    this.dialog = true;
  }

  saveImage(image: any) {
    const userRequest = UserRequest.createFromResponse(this.user);
    userRequest.imageData = image;
    this.hideImageDialog();
    this.saveUser(userRequest);
  }

  saveUser(userRequest: UserRequest) {

    this.userService.update(this.user.id, userRequest).subscribe({
      next: () => {
        this.loadData();
        this.messageToast.showSuccess('Successful', 'User updated.');
        this.hideDialog();
      },
      error: (error) => {
        this.handleError(error);
      }
    });
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
}
