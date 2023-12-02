import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-not-overview',
  templateUrl: './not-overview.component.html',
  styleUrls: ['./not-overview.component.css']
})
export class NotOverviewComponent {

  @Input()
  visible!: boolean;
  @Output()
  onModalHidden = new EventEmitter<any>();


  hideModal() {
    this.onModalHidden.emit();
  }

}
