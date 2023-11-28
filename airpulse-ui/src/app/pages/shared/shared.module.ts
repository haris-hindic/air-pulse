import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { RouterModule } from "@angular/router";
import { ButtonModule } from "primeng/button";
import { CheckboxModule } from "primeng/checkbox";
import { InputTextModule } from "primeng/inputtext";
import { DividerModule } from 'primeng/divider';


@NgModule({
    imports: [],
    declarations: [],
    exports: [CommonModule,
        ButtonModule,
        CheckboxModule,
        InputTextModule,
        FormsModule,
        BrowserAnimationsModule,
        RouterModule,
        DividerModule]
})
export class SharedModule { }