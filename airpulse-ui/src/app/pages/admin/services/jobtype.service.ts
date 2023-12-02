import { HttpClient } from "@angular/common/http";
import { BaseService } from "../../shared/base.service";
import { JobTypeRequest, JobTypeResponse } from "../model/jobtype.model";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class JobTypeService extends BaseService<JobTypeResponse, JobTypeRequest> {
  constructor(protected override http: HttpClient) {
    super(http, 'employee/job-type');
  }
}