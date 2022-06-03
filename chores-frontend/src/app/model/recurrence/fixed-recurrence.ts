import {Recurrence} from "./recurrence";
import {DateUnit} from "../date-unit.enum";

export interface FixedRecurrence extends Recurrence {
  frequency: number;
  dateUnit: DateUnit;
}
