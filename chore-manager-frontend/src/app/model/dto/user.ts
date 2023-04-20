import {BaseDto} from "./base-dto";
import {Household} from "./household";
import {Permission} from "../permission.enum";

export interface User extends BaseDto {
  email: string;
  image: string;
  households: Household[];
  permissions: Permission[];
}
