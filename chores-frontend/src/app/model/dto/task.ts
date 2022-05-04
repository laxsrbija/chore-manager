import { BaseDto } from "./base-dto";
import { Item } from "./item";
import { Recurrence } from "../recurrence/recurrence";

export interface Task extends BaseDto {
	description?: string;

	recurrence?: Recurrence;

	object?: Item;
	daysUntilNextRecurrence?: number;
}
