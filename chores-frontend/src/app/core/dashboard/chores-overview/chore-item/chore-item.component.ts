import { Component, Input } from '@angular/core';

import { Task } from "../../../../model/dto/task";

@Component({
	selector: 'app-chore-item',
	templateUrl: './chore-item.component.html',
	styleUrls: ['./chore-item.component.css']
})
export class ChoreItemComponent {

	@Input() task?: Task;

}
