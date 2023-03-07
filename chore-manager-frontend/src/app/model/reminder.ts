import {User} from "./dto/user";
import {DatePeriod} from "./date-period";

export interface ReminderInfo {

  reminderDate: DatePeriod;
  usersToNotify: User[];
}
