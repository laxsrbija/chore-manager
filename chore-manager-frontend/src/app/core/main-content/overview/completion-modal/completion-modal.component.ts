import {Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {User} from "../../../../model/dto/user";
import {RequestsService} from "../../../../service/requests.service";
import {Task} from "../../../../model/dto/task";

@Component({
  selector: 'app-completion-modal',
  templateUrl: './completion-modal.component.html',
  styleUrls: ['./completion-modal.component.scss']
})
export class CompletionModalComponent {

  saving = false;

  task?: Task;
  userId?: string;
  dateCompleted: string = new Date().toISOString().slice(0, 10);

  @Input() users?: Map<string, User[]>;
  @Output() changesSaved = new EventEmitter<any>();
  @ViewChild('close') close?: ElementRef;

  constructor(public authService: AuthService, public requestsService: RequestsService) {
  }

  openModal(task: Task) {
    this.saving = false;
    this.task = task;
    this.userId = this.authService.user?.id;
    this.dateCompleted = new Date().toISOString().slice(0, 10);
  }

  completeTask() {
    this.saving = true;
    this.requestsService.markTaskComplete(this.task!.id, this.userId, this.dateCompleted).subscribe(() => {
      this.close?.nativeElement.click();
      this.saving = false;
      this.changesSaved.emit();
    });
  }

  getUsers(): User[] {
    return this.users && this.task ? this.users.get(this.task.item.category.household.id) || [] : [];
  }
}
