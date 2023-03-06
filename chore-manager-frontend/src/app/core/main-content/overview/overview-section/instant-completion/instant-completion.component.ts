import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AuthService} from "../../../../../service/auth.service";
import {RequestsService} from "../../../../../service/requests.service";
import {CompletionHistoryItem} from "../../../../../model/completion-history-item";
import {Task} from 'src/app/model/dto/task';

@Component({
  selector: 'app-instant-completion',
  templateUrl: './instant-completion.component.html',
  styleUrls: ['./instant-completion.component.scss']
})
export class InstantCompletionComponent {

  @Input() task?: Task;
  @Output() changesSaved = new EventEmitter<any>();
  @Output() completeTask = new EventEmitter<string>();
  @Output() showCompletionHistory = new EventEmitter<CompletionHistoryItem[]>();

  saving = false;

  constructor(private authService: AuthService, private requestsService: RequestsService) {
  }

  markCompleted() {
    this.saving = true;
    this.requestsService.markTaskComplete(this.task!.id, this.authService.user!.id).subscribe(() => {
      this.saving = false;
      this.changesSaved.emit();
    });
  }
}
