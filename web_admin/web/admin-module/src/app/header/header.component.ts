import { Component, OnInit } from '@angular/core';
import { MenushareService } from '../share_data/menushare.service';
import { CookiesService } from '../services/cookies.service';
import { GlobalConstants } from '../GlobalConstants';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public menus =[];

  public langs =[];

  public display = 0;

  constructor(private router:Router,private cookieService: CookiesService,private _MenushareService:MenushareService) { }

  ngOnInit(): void {

    this._MenushareService.getmenu()
     .subscribe(mymessage => {
      this.menus = mymessage;
     if(mymessage === undefined){
      this.display = 0;
     }else{
      this.display = 1;
     }
   
     });


     this._MenushareService.getlang()
     .subscribe(mymessage => {
      this.langs = mymessage;

     });

  }



  
language(code){
  this.cookieService.language(code);
    }


    signout(){
      this.cookieService.username('',GlobalConstants.rember);
      this.cookieService.usertokean('',GlobalConstants.rember);
      window.location.replace("/login");
        }

}
