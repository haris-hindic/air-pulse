import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { FlightResponse, FlightRequest } from "../model/flight.model";

@Injectable({
    providedIn: 'root',
})
export class FlightService extends BaseService<FlightResponse, FlightRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'fc/flight', loader);
    }
}