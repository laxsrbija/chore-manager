import {Pipe, PipeTransform} from '@angular/core';
import {DateUnit} from "../model/date-unit.enum";

@Pipe({
  name: 'readableDateUnit'
})
export class DateUnitPipe implements PipeTransform {

  transform(value?: DateUnit): string {
    switch (value) {
      case DateUnit.DAY:
        return 'days';
      case DateUnit.WEEK:
        return 'weeks';
      case DateUnit.MONTH:
        return 'months';
      case DateUnit.YEAR:
        return 'years';
      default:
        return '';
    }
  }

}
