import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AuthModule } from './pages/auth/auth.module';
import { StyleClassModule } from 'primeng/styleclass';
import { PrimeNGConfig } from 'primeng/api';
import { HomeComponent } from './pages/home/home.component';
import { SharedModule } from './pages/shared/shared.module';
import { NotfoundComponent } from './pages/notfound/notfound.component';
import { LayoutModule } from './pages/layout/layout.module';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { UserModule } from './pages/user/user.module';
import { NotificationsModule } from './pages/notifications/notification.module';
import { MessageService } from 'primeng/api';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './pages/shared/services/auth-interceptor.service';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { FailureComponent } from './pages/failure/failure.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotfoundComponent,
    FailureComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    AuthModule,
    SharedModule,
    LayoutModule,
    UserModule,
    NotificationsModule,
    StyleClassModule,
    ProgressSpinnerModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    MessageService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private primengConfig: PrimeNGConfig) { }

  ngOnInit() {
    this.primengConfig.ripple = true;
  }
}
