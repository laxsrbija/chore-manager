import {Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {Task} from "../../../../model/dto/task";
import {RequestsService} from "../../../../service/requests.service";
import {TaskAction} from "../task-action.enum";

@Component({
  selector: 'app-defer-modal',
  templateUrl: './defer-modal.component.html',
  styleUrls: ['./defer-modal.component.scss']
})
export class DeferModalComponent {

  @Input() task?: Task;
  @ViewChild('close') close?: ElementRef;

  @Output() taskAction = new EventEmitter<[TaskAction, Task]>();

  deferDate: string = new Date().toISOString().slice(0, 10);

  constructor(private requestsService: RequestsService) {
  }

  deferTask() {
    this.requestsService.deferTask(this.task!.id!, this.deferDate).subscribe(() => {
      this.close?.nativeElement.click();
      this.taskAction.emit([TaskAction.SAVED, this.task!]);
    });
  }
}
