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

  isExpanded: boolean = false;

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

    return (daysUntilNextOccurrence > 0 ? 'In ' : '')
      + Math.abs(daysUntilNextOccurrence)
      + ' days'
      + (daysUntilNextOccurrence < 0 ? ' ago' : '');
  }

  getLastCompleted() {
    if (!this.task || !this.task.history) {
      return 'Never';
    }

    return this.task.history[this.task.history.length - 1].dateCompleted;
  }

  toggleDetails() {
    this.isExpanded = !this.isExpanded;
  }

}
