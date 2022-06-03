import {Component, EventEmitter, Input, Output} from '@angular/core';

import {Task} from "../../../../model/dto/task";

@Component({
  selector: 'app-chore-item',
  templateUrl: './chore-item.component.html',
  styleUrls: ['./chore-item.component.css']
})
export class ChoreItemComponent {

  @Input() task?: Task;
  @Output() markComplete = new EventEmitter<string>();

  getRelativeDateString() {
    if (!this.task) {
      return '';
    }

    const daysUntilNextOccurrence = this.task.occurrence ? this.task.occurrence.daysUntilNextOccurrence : 0;

    if (!daysUntilNextOccurrence) {
      return "Unknown";
    }

    if (daysUntilNextOccurrence === 1) {
      return 'Tomorrow';
    }

    if (daysUntilNextOccurrence === 0) {
      return 'Today';
    }

    if (daysUntilNextOccurrence === -1) {
      return 'Yesterday';
    }

    return Math.abs(daysUntilNextOccurrence) + ' days' + (daysUntilNextOccurrence < 0 ? ' ago' : '');
  }

  print() {
    console.log("AD");
  }
}
