import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { TopbarComponent } from "./topbar/topbar.component";
import { FooterComponent } from './footer/footer.component';

@NgModule({
    imports: [
        SharedModule
    ],
    declarations: [TopbarComponent, FooterComponent],
    exports: [TopbarComponent, FooterComponent]
})
export class LayoutModule { }