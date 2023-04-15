import {Injectable} from '@angular/core';
import {CookieService} from "ngx-cookie-service";
import {User} from "../model/dto/user";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private cookieKey = 'CMAUTHID';

  public user?: User;

  constructor(private cookieService: CookieService) {
  }

  public getAuthToken() {
    return this.cookieService.get(this.cookieKey);
  }

  public storeAuthToken(key: string) {
    this.cookieService.set(this.cookieKey, key);
  }

  public removeAuthToken() {
    this.cookieService.delete(this.cookieKey);
    this.user = undefined;
  }
}
