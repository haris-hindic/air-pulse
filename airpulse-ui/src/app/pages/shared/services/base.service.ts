import { HttpClient } from "@angular/common/http";
import { Observable, map } from "rxjs";
import { devEnvironment } from "src/environments/devenv";
import { ApiListResponse, ApiResponse } from "../model/api-response";
import { getQueryString, serialize } from "../utils/query-sting";
import { LoaderService } from "./loader.service";


export abstract class BaseService<TResponse, TRequest> {
  protected constructor(
    protected http: HttpClient,
    protected endpoint: string,
    protected loader: LoaderService
  ) {
  }
  url = `${devEnvironment.baseUrl}`;

  getAll(search = {}): Observable<TResponse[]> {
    this.loader.show();

    const queryString = getQueryString(search);
    let url = `${this.url}/${this.endpoint}`;
    if (queryString) {
      url = `${url}?${queryString}`;
    }

    return this.http.get<ApiListResponse<TResponse>>(`${url}`).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

  getById(id: number): Observable<TResponse> {
    this.loader.show();
    return this.http.get<ApiResponse<TResponse>>(`${this.url}/${this.endpoint}/${id}`).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

  create(entity: TRequest): Observable<TResponse> {
    this.loader.show();
    return this.http.post<ApiResponse<TResponse>>(`${this.url}/${this.endpoint}`, entity).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

  update(id: number, entity: TRequest): Observable<TResponse> {
    this.loader.show();
    return this.http.put<ApiResponse<TResponse>>(`${this.url}/${this.endpoint}/${id}`, entity).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

  delete(id: number): Observable<string> {
    this.loader.show();
    return this.http.delete<ApiResponse<string>>(`${this.url}/${this.endpoint}/${id}`).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

  bulkDelete(ids = {}): Observable<TResponse[]> {
    this.loader.show();

    console.log('ids :>> ', ids);
    const queryString = serialize(ids);
    let url = `${this.url}/${this.endpoint}/bulk-delete`;
    if (queryString) {
      url = `${url}?${queryString}`;
    }
    console.log('bulkdelete :>> ', url);
    return this.http.delete<ApiListResponse<TResponse>>(`${url}`).pipe(
      map((response) => {
        this.loader.hide();
        return response['data'];
      })
    );
  }

}
