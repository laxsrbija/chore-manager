import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Task} from "../../../../model/dto/task";
import {AuthService} from "../../../../service/auth.service";
import {TaskAction} from "../task-action.enum";

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

  @Output() taskAction = new EventEmitter<[TaskAction, Task]>();
}
