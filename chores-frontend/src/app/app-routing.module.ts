import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from "./core/dashboard/dashboard.component";
import { CategoriesComponent } from "./core/categories/categories.component";

const routes: Routes = [
	{ path: '', component: DashboardComponent },
	{ path: 'categories', component: CategoriesComponent },
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {
}
