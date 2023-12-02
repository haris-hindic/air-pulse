import { HttpClient } from "@angular/common/http";
import { devEnvironment } from "src/environments/devenv";
import { ApiResponse } from "../../shared/api-response";
import { Observable, map } from "rxjs";
import { LoginRequest } from "../model/login";
import { UserRequest } from "../model/user";
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  protected constructor(
    protected http: HttpClient
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
    return this.http.post<ApiResponse<string>>(`${this.url}/auth/login`, entity).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

  register(entity: UserRequest): Observable<string> {
    return this.http.post<ApiResponse<string>>(`${this.url}/auth/register`, entity).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

  test(): Observable<ApiResponse<any>> {
    return this.http.get<ApiResponse<any>>(`${this.url}/job-type`);
  }
}
