import { Component, OnInit } from '@angular/core';
import { TaskService } from "../../service/task.service";
import { Overview } from "../../model/dto/overview";

@Component({
	selector: 'app-dashboard',
	templateUrl: './dashboard.component.html',
	styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

	overview?: Overview;

	constructor(private taskService: TaskService) {
	}

	ngOnInit(): void {
		this.taskService.getOverview().subscribe(result => this.overview = result);
	}

}
