import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { StaffResponse, StaffRequest } from "../model/staff.model";

@Injectable({
    providedIn: 'root',
})
export class StaffService extends BaseService<StaffResponse, StaffRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'fc/staff', loader);
    }

}