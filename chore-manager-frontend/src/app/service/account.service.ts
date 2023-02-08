import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private cookieKey = 'CMAUTHID';

  public httpOptions?: {
    headers?: HttpHeaders;
  };

  constructor(private http: HttpClient, private cookieService: CookieService) {
    const key = this.cookieService.get(this.cookieKey);
    if (key) {
      this.httpOptions = this.getHeaders(key);
    }
  }

  login(httpOptions: any) {
    return this.http.post<void>('/api/service/account/login', {}, httpOptions);
  }

  getHeaders(key: string) {
    return {
      headers: new HttpHeaders(
        {
          'Content-Type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest',
          'Authorization': 'Basic ' + key,
        }
      )
    };
  }

  storeKey(key: string) {
    this.cookieService.set(this.cookieKey, key);
  }
}
