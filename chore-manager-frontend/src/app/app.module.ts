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
import {HttpClientModule} from "@angular/common/http";
import { OverviewSectionComponent } from './core/main-content/overview/overview-section/overview-section.component';

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
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
