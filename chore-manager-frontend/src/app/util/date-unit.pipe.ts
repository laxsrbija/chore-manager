import {Pipe, PipeTransform} from '@angular/core';
import {DateUnit} from "../model/date-unit.enum";

@Pipe({
  name: 'readableDateUnit'
})
export class DateUnitPipe implements PipeTransform {

  transform(value?: DateUnit): string {
    switch (value) {
      case DateUnit.DAY:
        return 'day(s)';
      case DateUnit.WEEK:
        return 'week(s)';
      case DateUnit.MONTH:
        return 'month(s)';
      case DateUnit.YEAR:
        return 'year(s)';
      default:
        return '';
    }
  }

}
