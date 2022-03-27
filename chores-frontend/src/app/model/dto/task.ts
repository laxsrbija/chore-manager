import { BaseDto } from "./base-dto";
import { Object } from "./object";

export interface Task extends BaseDto {
	object: Object;
	daysUntilNextRecurrence: number;
}
