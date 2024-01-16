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
import {Item} from "../../../model/dto/item";

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
  otherTasks?: Task[];
  disabledTasks?: Task[];

  users?: Record<string, User[]>;
  items?: Item[];

  constructor(private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    this.requestsService.getOverview().subscribe(overview => {
      this.overview = overview;
      this.requestsService.getUsersPerHousehold().subscribe(users => this.users = users);
      this.requestsService.getItems().subscribe(items => this.items = items);
    });
  }

  loadOverview() {
    this.requestsService.getOverview().subscribe(overview => this.overview = overview);

    if (this.otherTasks !== undefined) {
      this.loadOtherTasks();
    }

    if (this.disabledTasks !== undefined) {
      this.loadDisabledTasks();
    }
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
        this.taskModal?.loadModalData(event[1], this.items!, this.users!);
        break;
      case TaskAction.SHOW_POSTPONE:
        this.deferModal!.task = event[1];
        break;
    }
  }

  loadOtherTasks() {
    this.requestsService.getOtherTasks().subscribe(tasks => this.otherTasks = tasks);
  }

  loadDisabledTasks() {
    this.requestsService.getDisabledTasks().subscribe(tasks => this.disabledTasks = tasks);
  }
}
