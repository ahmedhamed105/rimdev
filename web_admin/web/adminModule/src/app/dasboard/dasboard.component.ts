import { Component, OnInit } from '@angular/core';
import { LocationServiceService } from '../services/location-service.service';
import {  Router, ActivatedRoute } from '@angular/router';
import { Idirectory } from '../objects/idirectory';
import { ComponentService } from '../services/component.service';

@Component({
  selector: 'app-dasboard',
  templateUrl: './dasboard.component.html',
  styleUrls: ['./dasboard.component.css']
})
export class DasboardComponent implements OnInit {

  public device ;
  public page : Idirectory;
  public type ;

  constructor(public _ComponentService: ComponentService,private router:Router,private route: ActivatedRoute,private locationService: LocationServiceService) { }

  ngOnInit() {

    
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };

    var menuid =this.route.snapshot.paramMap.get("id").toString();

    this.type =this.route.snapshot.paramMap.get("type");

    
    this._ComponentService.getmenu(this.type,menuid).subscribe(res =>{

      this.page=res;
  
      console.log(this.page)
  
      this.locationService.all_info(this.type === "P"?this.page.parent.pagesID.id:this.page.child.pagesID.id).then(res => {
        this.device =this.locationService.mydevice;
        console.log(this.device.devicetokean);
      });
    });

    
  }

}
