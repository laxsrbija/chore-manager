import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Category } from "../../../model/dto/category";

@Component({
  selector: 'app-category-modal',
  templateUrl: './category-modal.component.html',
  styleUrls: ['./category-modal.component.css']
})
export class CategoryModalComponent {

  @Input() category: Category = {};
  @Output() saveEvent = new EventEmitter<Category>();
  visible = false;

  createCategory() {
    this.category = {};
    this.visible = true;
  }

  editCategory(category: Category) {
    this.category = category;
    this.visible = true;
  }

  saveCategory() {
    this.saveEvent.emit(this.category);
    this.hide();
  }

  hide() {
    this.visible = false;
  }
}
