import { Component, OnInit } from '@angular/core';
import { MenushareService } from '../share_data/menushare.service';
import { LocationServiceService } from '../services/location-service.service';

@Component({
  selector: 'app-blocked',
  templateUrl: './blocked.component.html',
  styleUrls: ['./blocked.component.css']
})
export class BlockedComponent implements OnInit {
  public errorsatatus;
  public errormessage;

  constructor(private locationService: LocationServiceService) { }

  ngOnInit() {


    this.errorsatatus =  this.locationService.getQueryParams('status',window.location.href).replace(/%20/g,' ')|| 'None';
    this.errormessage =  this.locationService.getQueryParams('reason',window.location.href).replace(/%20/g,' ')|| 'None';
   


  }

}
