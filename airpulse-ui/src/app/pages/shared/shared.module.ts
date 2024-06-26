import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { ButtonModule } from "primeng/button";
import { CheckboxModule } from "primeng/checkbox";
import { InputTextModule } from "primeng/inputtext";
import { DividerModule } from 'primeng/divider';
import { CardModule } from 'primeng/card';
import { BadgeModule } from 'primeng/badge';
import { SkeletonModule } from 'primeng/skeleton';
import { AvatarModule } from 'primeng/avatar';
import { TabViewModule } from 'primeng/tabview';
import { ChipModule } from 'primeng/chip';
import { ImageModule } from 'primeng/image';
import { DialogModule } from 'primeng/dialog';
import { MenuModule } from 'primeng/menu';
import { TableModule } from 'primeng/table';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { TagModule } from 'primeng/tag';
import { ToastModule } from 'primeng/toast';
import { ToolbarModule } from 'primeng/toolbar';
import { RatingModule } from 'primeng/rating';
import { DropdownModule } from 'primeng/dropdown';
import { HttpClientModule } from '@angular/common/http';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { InputNumberModule } from 'primeng/inputnumber';
import { CalendarModule } from 'primeng/calendar';
import { ImageUploadComponent } from './image-upload/image-upload.component';
import { FileUploadModule } from 'primeng/fileupload';
import { DateTimeDiff } from "./pipe/timediff.pipe";
import { NgxStripeModule, StripeCardComponent } from "ngx-stripe";
import { ChartModule } from 'primeng/chart';

@NgModule({
  imports: [
    DialogModule,
    FileUploadModule,
    ImageModule, ChartModule,
    DateTimeDiff, NgxStripeModule.forRoot('pk_test_51KR05DIwNGlyHmAKnz8PDxOojJ1pXkmGAek19LTK4XY8oR6XAkpQgoZHE0ESAAdMjuFsT2QFV1NXSFAaW1XrLTdb00G3xxz2CI')
  ],
  declarations: [
    ImageUploadComponent
  ],
  exports: [CommonModule,
    ButtonModule,
    CheckboxModule,
    InputTextModule,
    FormsModule,
    RouterModule,
    DividerModule,
    CardModule,
    BadgeModule,
    SkeletonModule,
    AvatarModule,
    TabViewModule,
    ChipModule,
    ImageModule,
    DialogModule,
    MenuModule,
    TableModule,
    ConfirmDialogModule,
    TagModule,
    ToastModule,
    ToolbarModule,
    RatingModule,
    DropdownModule,
    HttpClientModule,
    ReactiveFormsModule,
    InputTextareaModule,
    InputNumberModule,
    CalendarModule,
    ImageUploadComponent, DateTimeDiff,
    StripeCardComponent, ChartModule

  ]
})
export class SharedModule { }