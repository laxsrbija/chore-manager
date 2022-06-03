import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from "./core/dashboard/dashboard.component";
import {MarkCompleteComponent} from "./core/mark-complete/mark-complete.component";

const routes: Routes = [
  {path: '', component: DashboardComponent},
  {path: 'complete', component: MarkCompleteComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
