import {DateUnit} from "./date-unit.enum";
import {RecurrenceType} from "./recurrence-type.enum";

export interface Recurrence {
  type: RecurrenceType;

  frequency: number;
  dateUnit: DateUnit;

  day?: number;
  month?: number;
}
