import { Component, OnInit } from '@angular/core';
import { LocationServiceService } from '../services/location-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dasboard',
  templateUrl: './dasboard.component.html',
  styleUrls: ['./dasboard.component.css']
})
export class DasboardComponent implements OnInit {

  public device ;
  public page_number;

  constructor(private route: ActivatedRoute,private locationService: LocationServiceService) { }

  ngOnInit() {
    this.page_number =this.route.snapshot.paramMap.get("id");
    
    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });

    
  }

}
