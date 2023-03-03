import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {UserService} from "../../service/user.service";
import {TaskService} from "../../service/task.service";
import {User} from "../../model/dto/user";
import {Task} from "../../model/dto/task";

@Component({
  selector: 'app-mark-complete',
  templateUrl: './mark-complete.component.html',
  styleUrls: ['./mark-complete.component.css']
})
export class MarkCompleteComponent implements OnInit {

  user?: User;
  task?: Task;

  constructor(private route: ActivatedRoute,
              private userService: UserService,
              private taskService: TaskService) {
  }

  ngOnInit(): void {
    this.route.queryParams
    .subscribe(params => {
      const taskId = params['taskId'];
      const userId = params['userId'];

      this.taskService.getTask(taskId).subscribe(task => {
        this.task = task;
        this.userService.getUser(userId).subscribe(user => {
          this.user = user;
          this.taskService.markComplete(taskId, userId, undefined).subscribe();
        });
      });
    });
  }

}
