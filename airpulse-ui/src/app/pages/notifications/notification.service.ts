import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { BaseService } from "../shared/services/base.service";
import { LoaderService } from "../shared/services/loader.service";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";
import { ApiResponse } from "../shared/model/api-response";
import { NotResponse } from "./model/notification.model";

@Injectable({
  providedIn: 'root',
})
export class NotificationService extends BaseService<NotResponse, null> {
  constructor(protected override http: HttpClient, protected override loader: LoaderService) {
    super(http, 'auth/notification', loader);
  }


  deactivate(id: any): Observable<string> {
    let url = `${devEnvironment.baseUrl}/auth/notification/deactivate/${id}`;
    this.loader.show();
    return this.http.put<ApiResponse<string>>(url, null).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

}