import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from "./core/dashboard/dashboard.component";
import { CategoriesComponent } from "./core/categories/categories.component";
import { ItemsComponent } from "./core/items/items.component";
import { TasksComponent } from "./core/tasks/tasks.component";

const routes: Routes = [
	{ path: '', component: DashboardComponent },
	{ path: 'categories', component: CategoriesComponent },
	{ path: 'items', component: ItemsComponent },
	{ path: 'tasks', component: TasksComponent },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
