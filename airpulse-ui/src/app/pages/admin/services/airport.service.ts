import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { AirportResponse, AirportRequest } from "../model/airport.model";

@Injectable({
    providedIn: 'root',
})
export class AirportService extends BaseService<AirportResponse, AirportRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'fc/airport', loader);
    }
}