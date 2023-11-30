import { NgModule } from "@angular/core";
import { SharedModule } from "../shared/shared.module";
import { UseradminComponent } from "./useradmin/useradmin.component";
import { RouterModule } from "@angular/router";
import { DetailsCardComponent } from './useradmin/details-card/details-card.component';
import { DetailsPanelComponent } from './useradmin/details-panel/details-panel.component';


@NgModule({
    imports: [
        SharedModule,
        RouterModule.forChild([
            {
                path: '', component: UseradminComponent, children: []
            },

            //{ path: '**', redirectTo: '/notfound' }
        ]),
    ],
    declarations: [
        UseradminComponent,
        DetailsCardComponent,
        DetailsPanelComponent
    ],
    exports: []
})
export class UserModule { }