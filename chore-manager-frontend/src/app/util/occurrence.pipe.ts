import {Pipe, PipeTransform} from '@angular/core';

@Pipe({
  name: 'occurrence'
})
export class OccurrencePipe implements PipeTransform {

  transform(value: number): string {
    return 'null';
  }

}
