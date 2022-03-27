import { BaseDto } from "./base-dto";
import { Category } from "./category";

export interface Object extends BaseDto {
	category: Category;
}
