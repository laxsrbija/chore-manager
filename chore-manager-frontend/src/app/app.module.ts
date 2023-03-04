import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {OverviewComponent} from './core/main-content/overview/overview.component';
import {MainContentComponent} from './core/main-content/main-content.component';
import {LoginComponent} from './core/login/login.component';
import {FooterComponent} from './shared/footer/footer.component';
import {NavbarComponent} from './shared/navbar/navbar.component';
import {OccurrencePipe} from './util/occurrence.pipe';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {
  OverviewSectionComponent
} from './core/main-content/overview/overview-section/overview-section.component';
import {AuthInterceptor} from "./service/auth.interceptor";
import {CookieService} from "ngx-cookie-service";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    OverviewComponent,
    MainContentComponent,
    LoginComponent,
    FooterComponent,
    NavbarComponent,
    OccurrencePipe,
    OverviewSectionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [
    CookieService,
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
