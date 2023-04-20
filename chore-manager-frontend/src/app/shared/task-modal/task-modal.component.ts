import {Component} from '@angular/core';
import {Task} from 'src/app/model/dto/task';
import {User} from "../../model/dto/user";
import {Item} from "../../model/dto/item";
import {RecurrenceType} from "../../model/recurrence-type.enum";
import {DateUnit} from "../../model/date-unit.enum";

@Component({
  selector: 'app-task-modal',
  templateUrl: './task-modal.component.html',
  styleUrls: ['./task-modal.component.scss']
})
export class TaskModalComponent {

  protected readonly RecurrenceType = RecurrenceType;
  protected readonly DateUnit = DateUnit;

  task?: Task;
  items?: Item[];
  users?: Record<string, User[]>;

  loadModalData(task: Task, items: Item[], users: Record<string, User[]>) {
    this.task = {...task};
    this.users = users;
    this.items = items;
  }

  userShouldBeReminded(user: User, task?: Task) {
    return task?.reminder.usersToNotify.map(u => u.id).includes(user.id);
  }

  onItemChange(item: Item) {
    if (this.task!.item.category.household.id !== item.category.household.id) {
      this.task!.reminder.usersToNotify = [];
    }

    this.task!.item = item;
  }

  onUserToggle($event: Event, user: User) {
    const checked = (<HTMLInputElement>$event.target).checked;
    if (checked) {
      this.task!.reminder.usersToNotify.push(user);
    } else {
      this.task!.reminder.usersToNotify = this.task!.reminder.usersToNotify.filter(u => u.id !== user.id);
    }
  }
}
