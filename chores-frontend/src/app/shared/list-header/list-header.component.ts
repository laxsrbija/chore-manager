import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-list-header',
  templateUrl: './list-header.component.html',
  styleUrls: ['./list-header.component.css']
})
export class ListHeaderComponent {

  @Input() headerTitle?: string;
  @Input() buttonLabel?: string;

  @Output() clickEvent = new EventEmitter<void>();

}
