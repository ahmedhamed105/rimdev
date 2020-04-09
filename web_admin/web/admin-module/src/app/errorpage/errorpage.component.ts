import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-errorpage',
  templateUrl: './errorpage.component.html',
  styleUrls: ['./errorpage.component.css']
})
export class ErrorpageComponent implements OnInit {

  public errorsatatus;
  public errormessage;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    console.log("as")
    this.errorsatatus =  this.getQueryParams('status',window.location.href)|| 'None';
    this.errormessage =  this.getQueryParams('reason',window.location.href)|| 'None';
   
    }

     getQueryParams( params, url ) {
  
      let href = url;
      //this expression is to get the query strings
      let reg = new RegExp( '[?&]' + params + '=([^&#]*)', 'i' );
      let queryString = reg.exec(href);
      return queryString ? queryString[1] : null;
    };

}
