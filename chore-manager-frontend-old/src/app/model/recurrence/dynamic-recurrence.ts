import {Recurrence} from "./recurrence";

export interface DynamicRecurrence extends Recurrence {
  day?: number;
  month?: number;
}
