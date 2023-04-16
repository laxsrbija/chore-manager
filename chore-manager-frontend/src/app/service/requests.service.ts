import {Injectable} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Overview} from "../model/dto/overview";
import {User} from "../model/dto/user";
import {Household} from "../model/dto/household";
import {Task} from "../model/dto/task";
import {Category} from "../model/dto/category";

@Injectable({
  providedIn: 'root'
})
export class RequestsService {

  constructor(private http: HttpClient) {
  }

  account() {
    return this.http.post<User>('/api/service/account', {});
  }

  accountWithOptions(httpOptions: any) {
    return this.http.post<any>('/api/service/account', {}, httpOptions);
  }

  getOverview() {
    return this.http.get<Overview>('/api/service/overview');
  }

  getHouseholds() {
    return this.http.get<Household[]>('/api/service/management/households');
  }

  saveHousehold(household: Household) {
    return this.http.post<void>('/api/service/management/households', household);
  }

  getCategories(householdId: string | null) {
    let params = new HttpParams();

    if (householdId) {
      params = params.append('householdId', householdId);
    }

    return this.http.get<Category[]>('/api/service/management/categories', {
      params: params
    });
  }

  saveCategory(category: Category) {
    return this.http.post<void>('/api/service/management/categories', category);
  }

  getUsersPerHousehold() {
    return this.http.get<Record<string, User[]>>('/api/service/management/users');
  }

  markTaskComplete(taskId: string, userId?: string, dateCompleted?: string) {
    let params = new HttpParams();

    if (userId) {
      params = params.append('userId', userId);
    }

    if (dateCompleted) {
      params = params.append('dateCompleted', dateCompleted);
    }

    return this.http.patch<Task>('/api/rest/tasks/' + taskId, undefined, {
        params: params
      }
    );
  }
}
