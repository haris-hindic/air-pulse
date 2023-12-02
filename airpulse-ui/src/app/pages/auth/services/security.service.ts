import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class SecurityService {
  private tokenKey: string = 'token';
  private roleKey: string = 'role';
  constructor() { }

  isAuthenticated() {
    const token = localStorage.getItem(this.tokenKey);

    if (!token) return false;

    const expirationDate = this.getFieldFromJWT("exp");
    var date = new Date(expirationDate * 1000);

    if (date <= new Date()) {
      this.logout();
      return false;
    }

    return true;
  }

  isAdmin() {
    const role = this.getFieldFromJWT(this.roleKey);

    if (role === 'ADMIN') return true;

    return false;
  }

  saveToken(token: string) {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken() {
    return localStorage.getItem(this.tokenKey);
  }

  getFieldFromJWT(field: string) {
    const token = localStorage.getItem(this.tokenKey);

    if (!token) return '';
    const dataToken = JSON.parse(atob(token.split('.')[1]));
    return dataToken[field];
  }

  logout() {
    localStorage.removeItem(this.tokenKey);
  }

}