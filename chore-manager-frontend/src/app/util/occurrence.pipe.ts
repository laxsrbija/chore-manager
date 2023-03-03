import {Pipe, PipeTransform} from '@angular/core';
import {Task} from '../model/dto/task';

@Pipe({
  name: 'occurrence'
})
export class OccurrencePipe implements PipeTransform {

  transform(task: Task): string {
    if (!task.enabled) {
      return 'Disabled';
    }

    const daysUntilNextOccurrence = task.occurrence.daysUntilNextOccurrence;

    if (!daysUntilNextOccurrence && task.history.length === 0) {
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

}
