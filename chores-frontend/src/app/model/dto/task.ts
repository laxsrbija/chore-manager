import {BaseDto} from "./base-dto";
import {Item} from "./item";
import {OccurrenceInfo} from "../occurrence";
import {ReminderInfo} from "../reminder";

export interface Task extends BaseDto {
  description?: string;

  occurrence?: OccurrenceInfo;
  reminder?: ReminderInfo;

  item?: Item;
}
