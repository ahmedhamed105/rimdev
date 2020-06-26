import { Component, OnInit } from '@angular/core';
import { LocationServiceService } from '../services/location-service.service';

@Component({
  selector: 'app-errorpage',
  templateUrl: './errorpage.component.html',
  styleUrls: ['./errorpage.component.css']
})
export class ErrorpageComponent implements OnInit {

  public errorsatatus;
  public errormessage;

  constructor(private locationService: LocationServiceService) { }

  ngOnInit() {
    //console.log("as")
    this.errorsatatus =  this.locationService.getQueryParams('status',window.location.href).replace(/%20/g,' ')|| 'None';
    this.errormessage =  this.locationService.getQueryParams('reason',window.location.href).replace(/%20/g,' ')|| 'None';
   
    }

   

}
