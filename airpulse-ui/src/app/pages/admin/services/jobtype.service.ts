import { HttpClient } from "@angular/common/http";
import { BaseService } from "../../shared/services/base.service";
import { JobTypeRequest, JobTypeResponse } from "../model/jobtype.model";
import { Injectable } from "@angular/core";
import { LoaderService } from "../../shared/services/loader.service";

@Injectable({
  providedIn: 'root',
})
export class JobTypeService extends BaseService<JobTypeResponse, JobTypeRequest> {
  constructor(protected override http: HttpClient, protected override loader: LoaderService) {
    super(http, 'employee/job-type', loader);
  }
}