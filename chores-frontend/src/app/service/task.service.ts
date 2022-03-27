import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Overview } from "../model/dto/overview";

@Injectable({
	providedIn: 'root'
})
export class TaskService {

	constructor(private http: HttpClient) {
	}

	getOverview() {
		return this.http.get<Overview>('/api/tasks/overview')
	}
}
