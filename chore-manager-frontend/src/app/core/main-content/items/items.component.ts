import {Component, OnInit} from '@angular/core';
import {Item} from "../../../model/dto/item";
import {Category} from "../../../model/dto/category";
import {RequestsService} from "../../../service/requests.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.scss']
})
export class ItemsComponent implements OnInit {

  items: Item[] = [];
  categories: Category[] = [];
  categoryId: string | null = null;

  constructor(private requestsService: RequestsService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(parameters => {
      this.categoryId = parameters['categoryId'];

      this.requestsService.getItems(this.categoryId).subscribe(items => this.items = items);
    });

    this.requestsService.getCategories().subscribe(categories => this.categories = categories);
  }

  selectedCategory() {
    return this.categories.find(category => this.categoryId && category.id == this.categoryId);
  }

  saveItem(item: Item) {
    this.requestsService.saveItem(item).subscribe(() => this.ngOnInit());
  }

  addItem() {
    this.items.unshift({
      category: {
        id: this.categories[0].id,
        household: {}
      }
    });
  }
}
