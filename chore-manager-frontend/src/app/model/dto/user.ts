import {BaseDto} from "./base-dto";
import {Household} from "./household";

export interface User extends BaseDto {
  email: string;
  image: string;
  household: Household[];
}
