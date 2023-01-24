import {BaseDto} from "./base-dto";
import {Category} from "./category";

export interface Item extends BaseDto {
  category: Category;
}
