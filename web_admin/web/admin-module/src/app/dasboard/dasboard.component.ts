import { Component, OnInit, Renderer2, ElementRef } from '@angular/core';
import { LocationServiceService } from '../services/location-service.service';
import {  Router, ActivatedRoute } from '@angular/router';
import { ComponentService } from '../services/component.service';
import { EncryptionService } from '../services/encryption.service';
import { MenulistService } from '../services/menulist.service';
import { CookiesService } from '../services/cookies.service';
import { LanguagegoService } from '../services/languagego.service';
import { GlobalConstants } from '../GlobalConstants';
import { UsersService } from '../services/users.service';
import { MenushareService } from '../share_data/menushare.service';
import { SpinnerService } from '../services/spinner.service';
@Component({
  selector: 'app-dasboard',
  templateUrl: './dasboard.component.html',
  styleUrls: ['./dasboard.component.css']
})
export class DasboardComponent implements OnInit {

  public device ;
  public page ;
  public type ;
  public pagenumber ;
 

  constructor(private spinnerService: SpinnerService,private renderer:Renderer2,private elementRef: ElementRef,private _MenushareService:MenushareService,private UsersService:UsersService,private _LanguagegoService :LanguagegoService,private cookieService: CookiesService,private _MenulistService : MenulistService,private _EncryptionService:EncryptionService,public _ComponentService: ComponentService,private locationService: LocationServiceService) { }
  
  
 

  ngOnInit() {

    var menuid ='1';
    this.pagenumber = '1';
    this.type ='P';



   
    this.cookieService.checkboxrember('1');

    GlobalConstants.rember = this.cookieService.getCookie('rember');
  if( GlobalConstants.rember === '1'){
    GlobalConstants.USERNAME = this.cookieService.getCookie('username');
    GlobalConstants.USERTOKEANkey = this.cookieService.getCookie('usertokean');
  }
  


    var lang = this.cookieService.getCookie('language') ;

    if(lang === ""){

      this.cookieService.language("EN");
    }else{
      GlobalConstants.language = lang; // To Get Cookie


    }


    GlobalConstants.pageid = this.pagenumber;


this.locationService.all_info().then(res => {

  this.device =this.locationService.mydevice;

  this.cookieService.username(this.device['username'],GlobalConstants.rember);
  this.cookieService.usertokean(this.device['usertokean'],GlobalConstants.rember);


  this._MenushareService.updateuser(this.device.userid);
  this._MenushareService.updateapp(this.device.app);
  this._MenushareService.updatenotif(this.device.notif);



      this._ComponentService.getmenu(this.type,menuid).subscribe(res =>{

        this.page=res;
    
        console.log(this.page)
  
     //   this.pagenumber = this.type === "P"?this.page['parent']['pagesID']['id']:this.page['child']['pagesID']['id'];
    
    //    GlobalConstants.pageid = this.pagenumber;

  

var lang=this.cookieService.getCookie('language');
    if(lang === ""){

      this.cookieService.language("EN");
    }else{
      GlobalConstants.language = lang; // To Get Cookie


    }

    this._LanguagegoService.getalllang().subscribe(data => {
 //this.langs=data;
 this._MenushareService.updatelang(data);
    });

    this._MenulistService.getmenu()
.subscribe(data => {
  //this.menus =data;
  this._MenushareService.updatemenu(data);


  });



 
});
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
