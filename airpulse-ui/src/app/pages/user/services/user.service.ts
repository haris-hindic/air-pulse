import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../../shared/services/base.service";
import { LoaderService } from "../../shared/services/loader.service";
import { UserRequest, UserResponse } from "../../auth/model/user";

@Injectable({
    providedIn: 'root',
})
export class UserService extends BaseService<UserResponse, UserRequest> {
    constructor(protected override http: HttpClient, protected override loader: LoaderService) {
        super(http, 'auth/user', loader);
    }

}