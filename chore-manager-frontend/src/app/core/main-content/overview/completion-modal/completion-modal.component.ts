import {Component, ElementRef, EventEmitter, Input, Output, ViewChild} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {User} from "../../../../model/dto/user";
import {RequestsService} from "../../../../service/requests.service";

@Component({
  selector: 'app-completion-modal',
  templateUrl: './completion-modal.component.html',
  styleUrls: ['./completion-modal.component.scss']
})
export class CompletionModalComponent {

  saving = false;

  taskId?: string;
  userId?: string;
  dateCompleted: string = new Date().toISOString().slice(0, 10);

  @Input() users?: User[];
  @Output() changesSaved = new EventEmitter<any>();
  @ViewChild('close') close?: ElementRef;

  constructor(public authService: AuthService, public requestsService: RequestsService) {
  }

  openModal(taskId: string) {
    this.saving = false;
    this.taskId = taskId;
    this.userId = this.authService.user?.id;
    this.dateCompleted = new Date().toISOString().slice(0, 10);
  }

  completeTask() {
    this.saving = true;
    this.requestsService.markTaskComplete(this.taskId!, this.userId, this.dateCompleted).subscribe(() => {
      this.close?.nativeElement.click();
      this.saving = false;
      this.changesSaved.emit();
    });
  }
}
