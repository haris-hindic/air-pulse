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
import { TopbarComponent } from './pages/layout/topbar/topbar.component';
import { LayoutModule } from './pages/layout/layout.module';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NotfoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    AuthModule,
    SharedModule,
    LayoutModule,
    StyleClassModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(private primengConfig: PrimeNGConfig) { }

  ngOnInit() {
    this.primengConfig.ripple = true;
  }
}
