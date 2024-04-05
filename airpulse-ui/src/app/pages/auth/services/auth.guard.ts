import { inject } from "@angular/core";
import { CanActivateFn, ActivatedRouteSnapshot, RouterStateSnapshot, Router, CanActivateChildFn } from "@angular/router";
import { SecurityService } from "./security.service";

export const canActivate: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const securityService = inject(SecurityService);
  const router = inject(Router);
  if (securityService.isAuthenticated()) {
    return true;
  }

  router.navigate(['auth/login']);
  return false;
};

export const canActivateAdmin: CanActivateFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => {
  const securityService = inject(SecurityService);
  const router = inject(Router);
  if (securityService.isAuthenticated() && securityService.isAdmin()) {
    return true;
  }

  router.navigate(['notfound']);
  return false;
};

export const canActivateChild: CanActivateChildFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => canActivate(route, state);

export const canActivateChildAdmin: CanActivateChildFn = (
  route: ActivatedRouteSnapshot,
  state: RouterStateSnapshot
) => canActivateAdmin(route, state);