import {BaseDto} from "./base-dto";
import {Item} from "./item";
import {OccurrenceInfo} from "../occurrence";
import {ReminderInfo} from "../reminder";
import {CompletionHistoryItem} from "../completion-history-item";

export interface Task extends BaseDto {
  description?: string;
  item?: Item;
  occurrence?: OccurrenceInfo;
  reminder?: ReminderInfo;
  history: CompletionHistoryItem[];
  enabled: boolean;
}
