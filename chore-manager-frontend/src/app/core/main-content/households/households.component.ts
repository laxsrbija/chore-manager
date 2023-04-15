import {Component, OnInit} from '@angular/core';
import {Household} from "../../../model/dto/household";
import {RequestsService} from "../../../service/requests.service";

@Component({
  selector: 'app-households',
  templateUrl: './households.component.html',
  styleUrls: ['./households.component.scss']
})
export class HouseholdsComponent implements OnInit {

  households: Household[] = [];

  constructor(private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    this.requestsService.getHouseholds().subscribe(households => this.households = households);
  }

  saveHousehold(household: Household) {
    this.requestsService.saveHousehold(household).subscribe(() => this.ngOnInit());
  }
}
