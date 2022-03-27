import { Component, Input } from '@angular/core';

import { Task } from "../../../../model/dto/task";
import { TaskService } from "../../../../service/task.service";

@Component({
	selector: 'app-chore-item',
	templateUrl: './chore-item.component.html',
	styleUrls: ['./chore-item.component.css']
})
export class ChoreItemComponent {

	@Input() task?: Task;

	constructor(private taskService: TaskService) {
	}

	markComplete() {
		if (this.task)
		{
			this.taskService.markComplete(this.task.id).subscribe();
		}
	}
}
