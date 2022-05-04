import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Task } from "../../../model/dto/task";

@Component({
  selector: 'app-task-modal',
  templateUrl: './task-modal.component.html',
  styleUrls: ['./task-modal.component.css']
})
export class TaskModalComponent {

  @Input() task: Task = {};
  @Output() saveEvent = new EventEmitter<Task>();
  visible = true;

  createItem() {
    this.task = {};
    this.visible = true;
  }

  editItem(task: Task) {
    this.task = task;
    this.visible = true;
  }

  saveItem() {
    this.saveEvent.emit(this.task);
    this.hide();
  }

  hide() {
    this.visible = false;
  }

}
