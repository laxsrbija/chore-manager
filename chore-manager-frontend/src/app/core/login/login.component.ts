import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {RequestsService} from "../../service/requests.service";
import {HttpHeaders} from "@angular/common/http";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  failed = false;
  email = "";
  password = "";

  constructor(
    private authService: AuthService,
    private router: Router,
    private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    if (this.authService.user !== undefined) {
      this.router.navigate(['/'], {}).then();
    }
  }

  login() {
    const key = btoa(this.email + ':' + this.password)
    const httpOptions = {
      headers: new HttpHeaders(
        {
          'Content-Type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest',
          'Authorization': 'Basic ' + key,
        }
      )
    };

    this.requestsService.accountWithOptions(httpOptions).subscribe(
      {
        next: () => {
          this.authService.storeAuthToken(key);
          this.router.navigate(['/'], {}).then();
        },
        error: () => {
          this.failed = true;
        }
      });
  }
}
