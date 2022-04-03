import { Component } from '@angular/core';

@Component({
  selector: 'app-item',
  templateUrl: './item.component.html',
  styleUrls: ['./item.component.css']
})
export class ItemComponent {

  isExpanded = false;

  toggleTasks() {
    this.isExpanded = !this.isExpanded;
  }

}
