import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { FlightResponse, FlightRequest } from "../model/flight.model";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";
import { ApiListResponse, ApiResponse } from "../../shared/model/api-response";
import { serialize } from "../../shared/utils/query-sting";
import { FlightBookingRequest, FlightBookingResponse } from "../model/flightbooking.model";

@Injectable({
    providedIn: 'root',
})
export class FlightBookingService extends BaseService<FlightBookingResponse, FlightBookingRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'fc/flight-booking', loader);
    }

    confirmBooking(bookingId: any): Observable<string> {
        let url = `${devEnvironment.baseUrl}/fc/flight-booking/confirm/${bookingId}`;
        this.loader.show();
        return this.http.put<ApiResponse<string>>(url, null).pipe(
            map((response) => {
                this.loader.hide();
                return response['data'];
            })
        );
    }

    cancelBooking(bookingId: any): Observable<string> {
        let url = `${devEnvironment.baseUrl}/fc/flight-booking/cancel/${bookingId}`;
        this.loader.show();
        return this.http.put<ApiResponse<string>>(url, null).pipe(
            map((response) => {
                this.loader.hide();
                return response['data'];
            })
        );
    }
}