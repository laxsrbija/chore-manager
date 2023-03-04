import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Overview} from "../model/dto/overview";
import {User} from "../model/dto/user";
import {Task} from "../model/dto/task";

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) {
  }

  account() {
    return this.http.post<User>('/api/account', {});
  }

  accountWithOptions(httpOptions: any) {
    return this.http.post<any>('/api/account', {}, httpOptions);
  }

  getOverview() {
    return this.http.get<Overview>('/api/overview');
  }

  getUsers() {
    return this.http.get<User[]>('/api/users');
  }

  markTaskComplete(taskId: string, userId?: string, dateCompleted?: string) {
    let params = new HttpParams();

    if (userId) {
      params = params.append('userId', userId);
    }

    if (dateCompleted) {
      params = params.append('dateCompleted', dateCompleted);
    }

    return this.http.patch<Task>('/api/tasks/' + taskId, undefined, {
        params: params
      }
    );
  }
}
