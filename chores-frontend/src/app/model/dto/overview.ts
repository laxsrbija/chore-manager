import {Task} from "./task";

export interface Overview {
  upcoming: Task[];
  overdue: Task[];

  taskCount: number;
  itemCount: number;
  categoryCount: number;
  userCount: number;
}
