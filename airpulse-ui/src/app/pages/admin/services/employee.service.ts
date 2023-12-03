import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { EmployeeRequest, EmployeeResponse } from "../model/employee.model";

@Injectable({
    providedIn: 'root',
})
export class EmployeeService extends BaseService<EmployeeResponse, EmployeeRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'emp/employee', loader);
    }
}