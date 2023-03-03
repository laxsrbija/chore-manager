import {Component} from '@angular/core';
import {AccountService} from "../../service/account.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  failed = false;
  email = "";
  password = "";

  constructor(private accountService: AccountService, private router: Router) {
  }

  login() {
    const key = btoa(this.email + ':' + this.password)
    const httpOptions = this.accountService.getHeaders(key);
    this.accountService.login(httpOptions).subscribe(
      {
        next: () => {
          this.accountService.httpOptions = httpOptions;
          this.accountService.storeKey(key);
          this.router.navigate(['/'], {}).then();
        },
        error: () => {
          this.failed = true;
        }
      });
  }
}
