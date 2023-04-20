import {BaseDto} from "./base-dto";
import {Item} from "./item";
import {OccurrenceInfo} from "../occurrence";
import {ReminderInfo} from "../reminder";
import {CompletionHistoryItem} from "../completion-history-item";
import {Recurrence} from "../recurrence";
import {DeferInfo} from "../defer";

export interface Task extends BaseDto {
  description: string;
  dateCreated?: string;
  item: Item;
  recurrence: Recurrence;
  occurrence: OccurrenceInfo;
  defer?: DeferInfo;
  reminder: ReminderInfo;
  history: CompletionHistoryItem[];
  enabled: boolean;
}
