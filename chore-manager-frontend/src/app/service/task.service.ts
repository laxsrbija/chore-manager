import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Overview} from "../model/dto/overview";
import {Task} from "../model/dto/task";
import {AccountService} from "./account.service";
import {Router} from "@angular/router";
import {EMPTY} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  constructor(private http: HttpClient, private accountService: AccountService, private router: Router) {
  }

  getOverview() {
    const loginHeaders = this.accountService.httpOptions;
    if (loginHeaders) {
      return this.http.get<Overview>('/api/service/overview', loginHeaders);
    } else {
      this.router.navigate(['/login'], {}).then();
      return EMPTY;
    }
  }

  getTask(taskId: string) {
    const loginHeaders = this.accountService.httpOptions;
    if (loginHeaders) {
      return this.http.get<Task>('/api/rest/tasks/' + taskId, loginHeaders);
    } else {
      this.router.navigate(['/login'], {}).then();
      return EMPTY;
    }
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

    const loginHeaders = this.accountService.httpOptions;
    if (loginHeaders) {
      return this.http.patch<Task>('/api/rest/tasks/' + taskId, undefined, {
          params: params,
          headers: loginHeaders.headers
        }
      );
    } else {
      this.router.navigate(['/login'], {}).then();
      return EMPTY;
    }
  }
}
