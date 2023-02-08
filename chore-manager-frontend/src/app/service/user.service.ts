import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/dto/user";
import {AccountService} from "./account.service";
import {Router} from "@angular/router";
import {EMPTY} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private accountService: AccountService, private router: Router) {
  }

  getAllUsers() {
    const loginHeaders = this.accountService.httpOptions;
    if (loginHeaders) {
      return this.http.get<User[]>('/api/service/account/users', loginHeaders);
    } else {
      this.router.navigate(['/login'], {}).then();
      return EMPTY;
    }
  }

  getUser(userId: string) {
    const loginHeaders = this.accountService.httpOptions;
    if (loginHeaders) {
      return this.http.get<User>('/api/service/account/users/' + userId, loginHeaders);
    } else {
      this.router.navigate(['/login'], {}).then();
      return EMPTY;
    }
  }
}
