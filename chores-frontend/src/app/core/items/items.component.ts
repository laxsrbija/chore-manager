import { Component, ViewChild } from '@angular/core';
import { ItemModalComponent } from "./item-modal/item-modal.component";
import { Item } from "../../model/dto/item";

@Component({
  selector: 'app-items',
  templateUrl: './items.component.html',
  styleUrls: ['./items.component.css']
})
export class ItemsComponent {

  @ViewChild('modal') modal?: ItemModalComponent;

  updateItem(item: Item) {
    console.log(item);
  }

}
