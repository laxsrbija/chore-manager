import {Component} from '@angular/core';
import {RequestsService} from "../../service/requests.service";
import {Router} from "@angular/router";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-main-content',
  templateUrl: './main-content.component.html',
  styleUrls: ['./main-content.component.scss']
})
export class MainContentComponent {

  constructor(requestsService: RequestsService, router: Router, authService: AuthService) {
    if (authService.getAuthToken()) {
      requestsService.account().subscribe(
        {
          next: user => {
            authService.user = user;
          },
          error: () => {
            router.navigate(['/login'], {}).then();
          }
        });
    } else {
      router.navigate(['/login'], {}).then();
    }
  }
}
