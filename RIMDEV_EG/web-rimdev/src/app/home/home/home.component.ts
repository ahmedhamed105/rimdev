import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LocationServiceService } from 'src/app/services/location-service.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private router: ActivatedRoute,private _http:HttpClient,private locationService: LocationServiceService) { }

  ngOnInit(): void {

   this.locationService.all_info().then(res => {

      console.log(res);

    });


  }



}
