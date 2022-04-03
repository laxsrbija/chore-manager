import { BaseDto } from "./base-dto";
import { Item } from "./item";

export interface Task extends BaseDto {
	object: Item;
	daysUntilNextRecurrence: number;
}
