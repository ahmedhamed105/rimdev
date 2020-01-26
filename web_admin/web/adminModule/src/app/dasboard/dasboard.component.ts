import { Component, OnInit } from '@angular/core';
import { LocationServiceService } from '../services/location-service.service';

@Component({
  selector: 'app-dasboard',
  templateUrl: './dasboard.component.html',
  styleUrls: ['./dasboard.component.css']
})
export class DasboardComponent implements OnInit {

  public device ;
  public page_number:number = 1 ;

  constructor(private locationService: LocationServiceService) { }

  ngOnInit() {

    
    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });

    
  }

}
