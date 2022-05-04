import { Component, EventEmitter, Input, Output } from '@angular/core';

@Component({
  selector: 'app-image-url-input',
  templateUrl: './image-url-input.component.html',
  styleUrls: ['./image-url-input.component.css']
})
export class ImageUrlInputComponent {

  @Input() label: string = "";
  @Input() url: string = "";
  @Output() changed = new EventEmitter<string>();

}
