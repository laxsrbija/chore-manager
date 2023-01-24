import {Task} from "./task";

export interface Overview {
  upcoming: Task[];
  overdue: Task[];
  disabled: Task[];

  taskCount: number;
  itemCount: number;
  categoryCount: number;
  userCount: number;

  build: string;
}
