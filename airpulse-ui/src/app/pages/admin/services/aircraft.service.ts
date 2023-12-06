import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { AircraftResponse, AircraftRequest } from "../model/aircraft.model";

@Injectable({
    providedIn: 'root',
})
export class AircraftService extends BaseService<AircraftResponse, AircraftRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'fc/aircraft', loader);
    }
}