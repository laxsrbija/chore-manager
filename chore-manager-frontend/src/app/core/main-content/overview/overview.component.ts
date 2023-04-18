import {Component, OnInit, ViewChild} from '@angular/core';
import {RequestsService} from "../../../service/requests.service";
import {Overview} from "../../../model/dto/overview";
import {User} from "../../../model/dto/user";
import {Task} from "../../../model/dto/task";
import {TaskAction} from "./task-action.enum";
import {CompletionModalComponent} from "./completion-modal/completion-modal.component";
import {HistoryModalComponent} from "./history-modal/history-modal.component";
import {TaskModalComponent} from "../../../shared/task-modal/task-modal.component";
import {DeferModalComponent} from "./defer-modal/defer-modal.component";

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.scss']
})
export class OverviewComponent implements OnInit {

  @ViewChild('completionModal') completionModal?: CompletionModalComponent;
  @ViewChild('completionHistoryModal') completionHistoryModal?: HistoryModalComponent;
  @ViewChild('taskModal') taskModal?: TaskModalComponent;
  @ViewChild('deferModal') deferModal?: DeferModalComponent;

  overview?: Overview;
  users?: Record<string, User[]>;

  constructor(private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    this.loadOverview();
    this.requestsService.getUsersPerHousehold().subscribe(users => this.users = users);
  }

  loadOverview() {
    this.requestsService.getOverview().subscribe(overview => this.overview = overview);
  }

  handleAction(event: [TaskAction, Task]) {
    switch (event[0]) {
      case TaskAction.SAVED:
        this.loadOverview();
        break;
      case TaskAction.SHOW_COMPLETE:
        this.completionModal?.openModal(event[1]);
        break;
      case TaskAction.SHOW_HISTORY:
        this.completionHistoryModal!.historyItems = event[1].history;
        break;
      case TaskAction.SHOW_DETAILS:
        this.taskModal!.task = event[1];
        break;
      case TaskAction.SHOW_POSTPONE:
        this.deferModal!.task = event[1];
        break;
    }
  }
}
