import { Component, ViewChild } from '@angular/core';
import { CategoryModalComponent } from "./category-modal/category-modal.component";
import { Category } from "../../model/dto/category";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent {

  @ViewChild('modal') modal?: CategoryModalComponent;

  updateCategory(category: Category) {
    console.log(category);
  }
}
