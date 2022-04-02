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
		if (this.task && this.task.id)
		{
			this.taskService.markComplete(this.task.id).subscribe();
		}
	}

	getRelativeDateString() {
		if (!this.task)
		{
			return '';
		}

		const daysUntilNextRecurrence = this.task.daysUntilNextRecurrence;

		if (daysUntilNextRecurrence === 1) {
			return 'Tomorrow';
		}

		if (daysUntilNextRecurrence === 0) {
			return 'Today';
		}

		if (daysUntilNextRecurrence === -1) {
			return 'Yesterday';
		}

		return Math.abs(daysUntilNextRecurrence) + ' days' + (daysUntilNextRecurrence < 0 ? ' ago' : '');
	}
}
