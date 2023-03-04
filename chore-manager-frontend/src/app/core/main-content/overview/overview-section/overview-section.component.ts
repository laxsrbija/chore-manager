import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Task} from "../../../../model/dto/task";

@Component({
  selector: 'app-overview-section',
  templateUrl: './overview-section.component.html',
  styleUrls: ['./overview-section.component.scss']
})
export class OverviewSectionComponent {

  @Input() tasks?: Task[];
  @Input() sectionTitle?: string;

  @Output() instantlyCompleteTask = new EventEmitter<any>();
  @Output() completeTask = new EventEmitter<string>();
}
