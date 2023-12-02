import { HttpClient } from "@angular/common/http";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";
import { getQueryString } from "./query-sting";
import { ApiListResponse, ApiResponse } from "./api-response";


export abstract class BaseService<TResponse, TRequest> {
  private endPoint;
  protected constructor(
    protected http: HttpClient,
    protected endpoint: string
  ) {
    this.endPoint = endpoint;
  }
  url = `${devEnvironment.baseUrl}`;

  getAll(search = {}): Observable<TResponse[]> {
    const queryString = getQueryString(search);
    let url = `${this.url}/${this.endpoint}`;
    if (queryString) {
      url = `${url}?${queryString}`;
    }

    return this.http.get<ApiListResponse<TResponse>>(`${url}`).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

  getById(id: number): Observable<TResponse> {
    return this.http.get<ApiResponse<TResponse>>(`${this.url}/${this.endpoint}/${id}`).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

  create(entity: TRequest): Observable<TResponse> {
    return this.http.post<ApiResponse<TResponse>>(`${this.url}/${this.endpoint}`, entity).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

  update(id: number, entity: TRequest): Observable<TResponse> {
    return this.http.put<ApiResponse<TResponse>>(`${this.url}/${this.endpoint}/${id}`, entity).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

  delete(id: number): Observable<string> {
    return this.http.delete<ApiResponse<string>>(`${this.url}/${this.endpoint}/${id}`).pipe(
      map((response) => {
        return response['data'];
      })
    );
  }

}
