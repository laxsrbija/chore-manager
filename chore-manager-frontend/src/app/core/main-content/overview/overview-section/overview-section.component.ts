import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Task} from "../../../../model/dto/task";
import {CompletionHistoryItem} from "../../../../model/completion-history-item";
import {AuthService} from "../../../../service/auth.service";

@Component({
  selector: 'app-overview-section',
  templateUrl: './overview-section.component.html',
  styleUrls: ['./overview-section.component.scss']
})
export class OverviewSectionComponent {

  constructor(public authService: AuthService) {
  }

  @Input() tasks?: Task[];
  @Input() sectionTitle?: string;

  @Output() instantlyCompleteTask = new EventEmitter<any>();
  @Output() completeTask = new EventEmitter<Task>();
  @Output() showTaskDetails = new EventEmitter<Task>();
  @Output() showCompletionHistory = new EventEmitter<CompletionHistoryItem[]>();
}
