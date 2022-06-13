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

  getNextOccurrence() {
    if (!this.task) {
      return '';
    }

    if (!this.task.enabled) {
      return 'Disabled';
    }

    const daysUntilNextOccurrence = this.task.occurrence ? this.task.occurrence.daysUntilNextOccurrence : 0;

    if (!daysUntilNextOccurrence && this.task.history.length === 0) {
      return 'New';
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
    if (this.task && this.task.history && this.task.history.length > 0) {
      return this.task.history[this.task.history.length - 1].dateCompleted;
    }

    return 'Never';
  }

  toggleDetails() {
    this.isExpanded = !this.isExpanded;
  }

}
