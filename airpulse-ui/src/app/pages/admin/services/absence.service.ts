import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { AbsenceResponse, AbsenceRequest } from "../model/absence.model";
import { ApiListResponse } from "../../shared/model/api-response";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";

@Injectable({
    providedIn: 'root',
})
export class AbsenceService extends BaseService<AbsenceResponse, AbsenceRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'emp/absence', loader);
    }

    getByEmployeeId(employeeId: number): Observable<AbsenceResponse[]> {
        this.loader.show();
        const url = `${devEnvironment.baseUrl}/emp/absence/employee/${employeeId}`;
        return this.http.get<ApiListResponse<AbsenceResponse>>(url).pipe(
            map((response) => {
                this.loader.hide();
                return response['data'];
            })
        );
    }
}