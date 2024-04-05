import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { HomeComponent } from "./pages/home/home.component";
import { NotfoundComponent } from "./pages/notfound/notfound.component";
import { canActivate, canActivateAdmin } from "./pages/auth/services/auth.guard";
import { FailureComponent } from "./pages/failure/failure.component";

const appRoutes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'notfound', component: NotfoundComponent },
  { path: 'failrue', component: FailureComponent },
  { path: 'auth', loadChildren: () => import('./pages/auth/auth.module').then(m => m.AuthModule) },
  { path: 'admin', loadChildren: () => import('./pages/admin/admin.module').then(m => m.AdminModule), canActivate: [canActivateAdmin] },
  { path: 'user', loadChildren: () => import('./pages/user/user.module').then(m => m.UserModule), canActivate: [canActivate] },
  { path: '**', component: NotfoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
