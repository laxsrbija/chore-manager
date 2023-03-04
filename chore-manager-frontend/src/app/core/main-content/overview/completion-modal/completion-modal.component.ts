import {Component, ElementRef, EventEmitter, OnInit, Output, ViewChild} from '@angular/core';
import {AuthService} from "../../../../service/auth.service";
import {RequestsService} from "../../../../service/requests.service";
import {User} from "../../../../model/dto/user";

@Component({
  selector: 'app-completion-modal',
  templateUrl: './completion-modal.component.html',
  styleUrls: ['./completion-modal.component.scss']
})
export class CompletionModalComponent implements OnInit {

  users?: User[];
  saving = false;

  taskId?: string;
  userId?: string;
  dateCompleted: string = new Date().toISOString().slice(0, 10);

  @Output() changesSaved = new EventEmitter<any>();
  @ViewChild('close') close?: ElementRef;

  constructor(public authService: AuthService, private requestsService: RequestsService) {
  }

  ngOnInit(): void {
    this.requestsService.getUsers().subscribe(users => this.users = users);
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
