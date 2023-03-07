import {Component} from '@angular/core';
import {CompletionHistoryItem} from "../../../../model/completion-history-item";

@Component({
  selector: 'app-history-modal',
  templateUrl: './history-modal.component.html',
  styleUrls: ['./history-modal.component.scss']
})
export class HistoryModalComponent {

  public historyItems?: CompletionHistoryItem[];

}
