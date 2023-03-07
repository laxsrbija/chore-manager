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
import { CompletionModalComponent } from './core/main-content/overview/completion-modal/completion-modal.component';
import { InstantCompletionComponent } from './core/main-content/overview/overview-section/instant-completion/instant-completion.component';
import { HistoryModalComponent } from './core/main-content/overview/history-modal/history-modal.component';
import { DateUnitPipe } from './util/date-unit.pipe';
import { TaskModalComponent } from './shared/task-modal/task-modal.component';

@NgModule({
  declarations: [
    AppComponent,
    OverviewComponent,
    MainContentComponent,
    LoginComponent,
    FooterComponent,
    NavbarComponent,
    OccurrencePipe,
    OverviewSectionComponent,
    CompletionModalComponent,
    InstantCompletionComponent,
    HistoryModalComponent,
    DateUnitPipe,
    TaskModalComponent
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
