import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-chores-overview',
  templateUrl: './chores-overview.component.html',
  styleUrls: ['./chores-overview.component.css']
})
export class ChoresOverviewComponent {

  @Input() sectionTitle: string | undefined;

}
