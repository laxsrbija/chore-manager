import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";

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
import { ItemsComponent } from './core/items/items.component';
import { ItemComponent } from './core/items/item/item.component';
import { ItemModalComponent } from './core/items/item-modal/item-modal.component';
import { TasksComponent } from './core/tasks/tasks.component';
import { ListHeaderComponent } from './shared/list-header/list-header.component';
import { TaskModalComponent } from './core/tasks/task-modal/task-modal.component';
import { TaskComponent } from "./core/tasks/task/task.component";
import { TaskShortcutsComponent } from './shared/task-shortcuts/task-shortcuts.component';
import { ImageUrlInputComponent } from './shared/image-url-input/image-url-input.component';

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
		CategoryModalComponent,
		ItemsComponent,
		ItemComponent,
		ItemModalComponent,
		TasksComponent,
		ListHeaderComponent,
		TaskModalComponent,
		TaskComponent,
		TaskShortcutsComponent,
		ImageUrlInputComponent
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
