import { Directive, ElementRef } from '@angular/core';
import * as $ from 'jquery';

@Directive({
  selector: '[icheck]'
})
export class IcheckDirective {

  constructor(el: ElementRef) {

    $(el).iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' /* optional */
    });
   }

}
