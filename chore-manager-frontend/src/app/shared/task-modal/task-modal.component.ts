import {Component, Input} from '@angular/core';
import {Task} from 'src/app/model/dto/task';
import {User} from "../../model/dto/user";

@Component({
  selector: 'app-task-modal',
  templateUrl: './task-modal.component.html',
  styleUrls: ['./task-modal.component.scss']
})
export class TaskModalComponent {

  public task?: Task;
  @Input() users?: Map<string, User[]>;

  userShouldBeReminded(user: User, task?: Task) {
    return task?.reminder.usersToNotify.map(u => u.id).includes(user.id);
  }
}
