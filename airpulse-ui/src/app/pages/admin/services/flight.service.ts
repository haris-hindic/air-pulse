import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { FlightResponse, FlightRequest } from "../model/flight.model";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";
import { ApiListResponse, ApiResponse } from "../../shared/model/api-response";
import { serialize } from "../../shared/utils/query-sting";

@Injectable({
    providedIn: 'root',
})
export class FlightService extends BaseService<FlightResponse, FlightRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'fc/flight', loader);
    }



    findReturnFlights(search: any): Observable<FlightResponse[]> {
        const queryString = serialize(search);
        let url = `${devEnvironment.baseUrl}/fc/flight/return`;
        if (queryString) {
            url = `${url}?${queryString}`;
        }

        this.loader.show();
        return this.http.get<ApiListResponse<FlightResponse>>(url).pipe(
            map((response) => {
                this.loader.hide();
                return response['data'];
            })
        );
    }

    createCheckout(request: any): Observable<string> {
        let url = `${devEnvironment.baseUrl}/fc/flight/create-checkout`;
        this.loader.show();
        return this.http.post<ApiResponse<string>>(url, request).pipe(
            map((response) => {
                this.loader.hide();
                return response['data'];
            })
        );
    }
}