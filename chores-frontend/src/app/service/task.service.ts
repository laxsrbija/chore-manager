import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Overview} from "../model/dto/overview";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient) {
  }

  getOverview() {
    return this.http.get<Overview>('/api/overview')
  }

  markComplete(taskId: string, userId: string, dateCompleted: string) {
    return this.http.patch('/api/tasks/' + taskId, undefined, {
      params: {
        "userId": userId,
        "dateCompleted": dateCompleted
      }
    });
  }
}
