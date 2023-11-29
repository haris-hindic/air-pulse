import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { RouterModule } from "@angular/router";
import { ButtonModule } from "primeng/button";
import { CheckboxModule } from "primeng/checkbox";
import { InputTextModule } from "primeng/inputtext";
import { DividerModule } from 'primeng/divider';
import { CardModule } from 'primeng/card';
import { BadgeModule } from 'primeng/badge';

@NgModule({
    imports: [],
    declarations: [],
    exports: [CommonModule,
        ButtonModule,
        CheckboxModule,
        InputTextModule,
        FormsModule,
        RouterModule,
        DividerModule,
        CardModule,
        BadgeModule
    ]
})
export class SharedModule { }