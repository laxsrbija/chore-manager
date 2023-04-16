import {Component, OnInit} from '@angular/core';
import {Category} from "../../../model/dto/category";
import {RequestsService} from "../../../service/requests.service";
import {Household} from "../../../model/dto/household";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.scss']
})
export class CategoriesComponent implements OnInit {

  categories: Category[] = [];
  households: Household[] = [];
  householdId: string | null = null;

  constructor(private requestsService: RequestsService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.householdId = this.route.snapshot.queryParamMap.get('householdId');

    this.requestsService.getCategories(this.householdId).subscribe(categories => this.categories = categories);
    this.requestsService.getHouseholds().subscribe(households => this.households = households);
  }

  selectedHousehold() {
    return this.households.find(household => this.householdId && household.id == this.householdId);
  }

  saveCategory(category: Category) {
    this.requestsService.saveCategory(category).subscribe(() => this.ngOnInit());
  }

  addCategory() {
    this.categories.push({household: this.households[0]})
  }
}
