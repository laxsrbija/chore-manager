import {BaseDto} from "./base-dto";
import {Household} from "./household";

export interface Category extends BaseDto {
  household: Household;
}
