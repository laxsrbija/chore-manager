import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AuthService} from "../../../../../service/auth.service";
import {RequestsService} from "../../../../../service/requests.service";
import {Task} from 'src/app/model/dto/task';
import {TaskAction} from "../../task-action.enum";

@Component({
  selector: 'app-instant-completion',
  templateUrl: './instant-completion.component.html',
  styleUrls: ['./instant-completion.component.scss']
})
export class InstantCompletionComponent {

  @Input() task?: Task;
  @Output() taskAction = new EventEmitter<[TaskAction, Task]>();

  saving = false;

  constructor(private authService: AuthService, private requestsService: RequestsService) {
  }

  markCompleted() {
    this.saving = true;
    this.requestsService.markTaskComplete(this.task!.id!, this.authService.user!.id).subscribe(() => {
      this.saving = false;
      this.taskAction.emit([TaskAction.SAVED, this.task!]);
    });
  }

  protected readonly TaskAction = TaskAction;
}
