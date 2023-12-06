import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { AircraftseatResponse, AircraftseatRequest } from "../model/aircraftseat.model";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";
import { ApiListResponse } from "../../shared/model/api-response";

@Injectable({
    providedIn: 'root',
})
export class AircraftSeatService extends BaseService<AircraftseatResponse, AircraftseatRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'fc/aircraft-seat', loader);
    }

    getByAircraftId(aircraftId: number): Observable<AircraftseatResponse[]> {
        this.loader.show();
        const url = `${devEnvironment.baseUrl}/fc/aircraft-seat/aircraft/${aircraftId}`;
        return this.http.get<ApiListResponse<AircraftseatResponse>>(url).pipe(
            map((response) => {
                this.loader.hide();
                return response['data'];
            })
        );
    }
}