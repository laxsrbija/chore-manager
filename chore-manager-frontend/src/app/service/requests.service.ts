import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Overview} from "../model/dto/overview";
import {User} from "../model/dto/user";

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
}
