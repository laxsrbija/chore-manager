import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from "@angular/common/http";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './core/navbar/navbar.component';
import {DashboardComponent} from './core/dashboard/dashboard.component';
import {FooterComponent} from './core/footer/footer.component';
import {ChoresOverviewComponent} from './core/dashboard/chores-overview/chores-overview.component';
import {ChoreItemComponent} from './core/dashboard/chores-overview/chore-item/chore-item.component';
import {
  CompleteTaskModalComponent
} from './shared/complete-task-modal/complete-task-modal.component';
import {TaskShortcutsComponent} from "./shared/task-shortcuts/task-shortcuts.component";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    DashboardComponent,
    FooterComponent,
    ChoresOverviewComponent,
    ChoreItemComponent,
    CompleteTaskModalComponent,
    TaskShortcutsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
