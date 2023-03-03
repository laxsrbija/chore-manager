import {Component, OnInit} from '@angular/core';
import {RequestsService} from "../../../service/requests.service";
import {Overview} from "../../../model/dto/overview";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  overview?: Overview;

  constructor(private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    this.requestsService.getOverview().subscribe(overview => this.overview = overview);
  }
}
