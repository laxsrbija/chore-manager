import { Component, Input } from '@angular/core';

import { Task } from "../../../model/dto/task";

@Component({
	selector: 'app-chores-overview',
	templateUrl: './chores-overview.component.html',
	styleUrls: ['./chores-overview.component.css']
})
export class ChoresOverviewComponent {

	@Input() sectionTitle?: string;
	@Input() tasks?: Task[];

}
