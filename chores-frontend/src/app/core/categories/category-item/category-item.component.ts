import { Component } from '@angular/core';

@Component({
  selector: 'app-category-item',
  templateUrl: './category-item.component.html',
  styleUrls: ['./category-item.component.css']
})
export class CategoryItemComponent {

  isExpanded = false;

  toggleItems() {
    this.isExpanded = !this.isExpanded;
  }
}
