import {Component} from '@angular/core';
import {Task} from 'src/app/model/dto/task';
import {User} from "../../model/dto/user";
import {Item} from "../../model/dto/item";

@Component({
  selector: 'app-task-modal',
  templateUrl: './task-modal.component.html',
  styleUrls: ['./task-modal.component.scss']
})
export class TaskModalComponent {

  task?: Task;
  items?: Item[];
  users?: Record<string, User[]>;

  loadModalData(task: Task, items: Item[], users: Record<string, User[]>) {
    this.task = task;
    this.users = users;
    this.items = items;
  }

  userShouldBeReminded(user: User, task?: Task) {
    return task?.reminder.usersToNotify.map(u => u.id).includes(user.id);
  }

  getUsers(): User[] {
    return this.users && this.task ? this.users[this.task.item.category.household.id!] : [];
  }
}
