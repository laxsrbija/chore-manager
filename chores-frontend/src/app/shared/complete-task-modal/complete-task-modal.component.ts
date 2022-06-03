import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TaskService} from "../../service/task.service";
import {UserService} from "../../service/user.service";
import {User} from "../../model/dto/user";

@Component({
  selector: 'app-complete-task-modal',
  templateUrl: './complete-task-modal.component.html',
  styleUrls: ['./complete-task-modal.component.css']
})
export class CompleteTaskModalComponent implements OnInit {

  taskId?: string;
  userId?: string;
  dateCompleted: string = new Date().toISOString().slice(0, 10);

  @Output() changesSaved = new EventEmitter<any>();

  users: User[] = [];
  visible = false;
  loading = false;

  constructor(
    private taskService: TaskService,
    private userService: UserService) {
  }

  openModal(taskId: string) {
    this.taskId = taskId;
    this.userId = undefined;

    this.visible = true;
  }

  ngOnInit() {
    this.userService.getAllUsers().subscribe(users => this.users = users);
  }

  markComplete() {
    if (this.taskId && this.userId) {
      this.loading = true;
      this.taskService.markComplete(this.taskId, this.userId, this.dateCompleted)
      .subscribe(() => {
        this.changesSaved.emit();
        this.loading = false;
        this.hide();
      });
    } else {
      this.hide();
    }
  }

  hide() {
    this.visible = false;
  }

}
