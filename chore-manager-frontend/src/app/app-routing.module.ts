import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OverviewComponent} from "./core/main-content/overview/overview.component";
import {LoginComponent} from "./core/login/login.component";
import {MainContentComponent} from "./core/main-content/main-content.component";
import {HouseholdsComponent} from "./core/main-content/households/households.component";
import {CategoriesComponent} from "./core/main-content/categories/categories.component";
import {ItemsComponent} from "./core/main-content/items/items.component";

const routes: Routes = [
  {
    path: '', component: MainContentComponent,
    children: [
      {
        path: '',
        component: OverviewComponent
      },
      {
        path: 'households',
        component: HouseholdsComponent
      },
      {
        path: 'categories',
        component: CategoriesComponent
      },
      {
        path: 'items',
        component: ItemsComponent
      }
    ]
  },
  {path: 'login', component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
