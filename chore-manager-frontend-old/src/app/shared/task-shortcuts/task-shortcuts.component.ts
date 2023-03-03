import {Component, EventEmitter, Output} from '@angular/core';

@Component({
  selector: 'app-task-shortcuts',
  templateUrl: './task-shortcuts.component.html',
  styleUrls: ['./task-shortcuts.component.css']
})
export class TaskShortcutsComponent {

  @Output() markComplete = new EventEmitter<any>();

}
