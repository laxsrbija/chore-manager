import {Component, OnInit, ViewChild} from '@angular/core';
import {Item} from "../../../model/dto/item";
import {Task} from "../../../model/dto/task";
import {RequestsService} from "../../../service/requests.service";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../../model/dto/user";
import {TaskModalComponent} from "../../../shared/task-modal/task-modal.component";
import {RecurrenceType} from "../../../model/recurrence-type.enum";
import {DateUnit} from "../../../model/date-unit.enum";

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.scss']
})
export class TasksComponent implements OnInit {

  @ViewChild('taskModal') taskModal?: TaskModalComponent;

  tasks: Task[] = [];
  items: Item[] = [];
  users?: Record<string, User[]>;
  itemId: string | null = null;

  constructor(private requestsService: RequestsService, private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(parameters => {
      this.itemId = parameters['itemId'];

      this.requestsService.getTasks(this.itemId).subscribe(tasks => {
        this.tasks = tasks;
        this.requestsService.getUsersPerHousehold().subscribe(users => this.users = users);
      });
    });


    this.requestsService.getItems().subscribe(item => this.items = item);
  }

  selectedItem() {
    return this.items.find(item => this.itemId && item.id == this.itemId);
  }

  addTask() {
    const task: Task = {
      description: '',
      occurrence: {daysUntilNextOccurrence: 0, nextOccurrence: '1970-01-01'},
      history: [],
      reminder: {reminderDate: {dateUnit: DateUnit.DAY, frequency: 0}, usersToNotify: []},
      item: this.items[0],
      enabled: true,
      recurrence: {type: RecurrenceType.DYNAMIC}
    };
    this.taskModal?.loadModalData(task, this.items, this.users!);
  }

  editTask(task: Task) {
    this.taskModal?.loadModalData(task, this.items, this.users!);
  }

  deleteTask(task: Task) {
    this.requestsService.deleteTask(task.id!).subscribe(() => {
      this.ngOnInit();
    });
  }
}
