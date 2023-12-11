import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FileSelectEvent } from 'primeng/fileupload';
import { imgToByte } from '../utils/image-utils';

@Component({
  selector: 'app-image-upload',
  templateUrl: './image-upload.component.html',
  styleUrls: ['./image-upload.component.css']
})
export class ImageUploadComponent {


  @Input()
  visible!: boolean;
  @Output()
  hideButtonClicked = new EventEmitter<any>();
  @Output()
  saveButtonClicked = new EventEmitter<any>();

  selectedImage: any;


  readFile = (event: any) => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();

      reader.onload = (res) => {
        this.selectedImage = res!.target!.result;
        resolve(res!.target!.result);
      };
      reader.onerror = (err) => reject(err);

      reader.readAsDataURL(event.files[0]);
    });
  };

  saveImage() { this.saveButtonClicked.emit(this.selectedImage); };
  hideDialog() { this.hideButtonClicked.emit(); }
}
