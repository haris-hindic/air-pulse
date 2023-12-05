import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";
import { ApiListResponse } from "../../shared/model/api-response";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { PositionResponse, PositionRequest } from "../model/position.model";


@Injectable({
    providedIn: 'root',
})
export class PositionService extends BaseService<PositionResponse, PositionRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'emp/position', loader);
    }

    getByEmployeeId(employeeId: number): Observable<PositionResponse[]> {
        this.loader.show();
        const url = `${devEnvironment.baseUrl}/emp/position/employee/${employeeId}`;
        return this.http.get<ApiListResponse<PositionResponse>>(url).pipe(
            map((response) => {
                this.loader.hide();
                return response['data'];
            })
        );
    }
}