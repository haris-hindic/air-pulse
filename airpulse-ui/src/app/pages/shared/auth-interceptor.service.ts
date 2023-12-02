import { HttpInterceptor, HttpRequest, HttpHandler } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { SecurityService } from "../auth/services/security.service";


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private securityService: SecurityService) {

  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = localStorage.getItem('token');
    if (token) {
      const username = this.securityService.getFieldFromJWT('username');
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + token,
          AP_USER: username,
          'Content-Type': 'application/json',
        },
      });
    }
    return next.handle(req);
  }
}