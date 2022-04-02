import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './core/navbar/navbar.component';
import { DashboardComponent } from './core/dashboard/dashboard.component';
import { FooterComponent } from './core/footer/footer.component';
import { ChoresOverviewComponent } from './core/dashboard/chores-overview/chores-overview.component';
import { ChoreItemComponent } from './core/dashboard/chores-overview/chore-item/chore-item.component';
import { CategoriesComponent } from './core/categories/categories.component';
import { CategoryItemComponent } from './core/categories/category-item/category-item.component';
import { CategoryModalComponent } from './core/categories/category-modal/category-modal.component';

@NgModule({
	declarations: [
		AppComponent,
		NavbarComponent,
		DashboardComponent,
		FooterComponent,
		ChoresOverviewComponent,
		ChoreItemComponent,
		CategoriesComponent,
		CategoryItemComponent,
		CategoryModalComponent
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
