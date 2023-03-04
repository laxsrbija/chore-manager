import {Component, EventEmitter, Input, Output} from '@angular/core';
import {AuthService} from "../../../../../service/auth.service";
import {RequestsService} from "../../../../../service/requests.service";

@Component({
  selector: 'app-instant-completion',
  templateUrl: './instant-completion.component.html',
  styleUrls: ['./instant-completion.component.scss']
})
export class InstantCompletionComponent {

  @Input() taskId?: string;
  @Output() changesSaved = new EventEmitter<any>();
  @Output() completeTask = new EventEmitter<string>();

  saving = false;

  constructor(private authService: AuthService, private requestsService: RequestsService) {
  }

  markCompleted() {
    this.saving = true;
    this.requestsService.markTaskComplete(this.taskId!, this.authService.user!.id).subscribe(() => {
      this.saving = false;
      this.changesSaved.emit();
    });
  }
}
