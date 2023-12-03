import { HttpClient } from "@angular/common/http";
import { devEnvironment } from "src/environments/devenv";
import { ApiResponse } from "../../shared/model/api-response";
import { Observable, catchError, map, of } from "rxjs";
import { LoginRequest } from "../model/login";
import { UserRequest } from "../model/user";
import { Injectable } from "@angular/core";
import { LoaderService } from "../../shared/services/loader.service";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  protected constructor(
    protected http: HttpClient, protected loader: LoaderService
  ) { }
  url = `${devEnvironment.baseUrl}/auth`;

  validateToken(token: string): Observable<boolean> {
    return this.http.get<ApiResponse<boolean>>(`${this.url}/auth/validate-token`).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

  login(entity: LoginRequest): Observable<string> {
    this.loader.show();
    return this.http.post<ApiResponse<string>>(`${this.url}/auth/login`, entity).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

  register(entity: UserRequest): Observable<string> {
    //this.loader.show();
    return this.http.post<ApiResponse<string>>(`${this.url}/auth/register`, entity).pipe(
      map((response) => {
        //this.loader.hide();
        return response['data'];
      })
    );
  }

}
