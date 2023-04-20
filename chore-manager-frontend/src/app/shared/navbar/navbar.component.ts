import {Component} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Permission} from "../../model/permission.enum";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  constructor(public authService: AuthService) {
  }

  signOut() {
    this.authService.removeAuthToken();
  }

  protected readonly Permission = Permission;
}
