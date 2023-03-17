import {Component, OnInit} from '@angular/core';
import {RequestsService} from "../../../service/requests.service";
import {Overview} from "../../../model/dto/overview";
import {User} from "../../../model/dto/user";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  overview?: Overview;
  users?: Map<string, User[]>;

  constructor(private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    this.loadOverview();
    this.requestsService.getUsersPerHousehold().subscribe(users => this.users = users);
  }

  loadOverview() {
    this.requestsService.getOverview().subscribe(overview => this.overview = overview);
  }
}
