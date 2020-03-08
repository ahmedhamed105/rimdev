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

   
    this.route
      .queryParamMap
      .subscribe(params => {
        console.log(params)
        this.errorsatatus = params.get('status') || 'None';
        this.errormessage = params.get('reason') || 'None';
      });

    }

}
