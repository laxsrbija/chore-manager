import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Category } from "../../../model/dto/category";
import { Item } from "../../../model/dto/item";

@Component({
  selector: 'app-item-modal',
  templateUrl: './item-modal.component.html',
  styleUrls: ['./item-modal.component.css']
})
export class ItemModalComponent {

  @Input() item: Item = {category: {}};
  @Output() saveEvent = new EventEmitter<Item>();
  visible = false;

  createItem() {
    this.item = {category: {}};
    this.visible = true;
  }

  editItem(item: Item) {
    this.item = item;
    this.visible = true;
  }

  saveItem() {
    this.saveEvent.emit(this.item);
    this.hide();
  }

  hide() {
    this.visible = false;
  }

}
