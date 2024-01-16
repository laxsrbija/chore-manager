import {Task} from "./task";

export interface Overview {
  upcoming: Task[];
  overdue: Task[];
}
