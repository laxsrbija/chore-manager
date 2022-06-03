import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Overview} from "../model/dto/overview";
import {Task} from "../model/dto/task";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) {
  }

  getOverview() {
    return this.http.get<Overview>('/api/overview')
  }

  getTask(taskId: string) {
    return this.http.get<Task>('/api/tasks/' + taskId);
  }

  markComplete(taskId: string, userId: string, dateCompleted?: string) {
    let params = new HttpParams({
      fromObject: {
        userId: userId
      }
    });

    if (dateCompleted) {
      params = params.append('dateCompleted', dateCompleted);
    }

    return this.http.patch<Task>('/api/tasks/' + taskId, undefined, {
        params: params
      }
    );
  }
}
