import { Component, OnInit } from '@angular/core';
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
  public pagetokean ;
 

  constructor(private _MenushareService:MenushareService,private UsersService:UsersService,private _LanguagegoService :LanguagegoService,private cookieService: CookiesService,private _MenulistService : MenulistService,private _EncryptionService:EncryptionService,public _ComponentService: ComponentService,private locationService: LocationServiceService) { }

  ngOnInit() {




    var menuid ='1';

    this.type ='P';

    GlobalConstants.rember = this.cookieService.getCookie('rember');
  if( GlobalConstants.rember === '1'){
    var username = this.cookieService.getCookie('username');
    var usertokean = this.cookieService.getCookie('usertokean');
    if(username === "" || usertokean === ""){
      window.location.replace("/login");
    }else{
      GlobalConstants.USERNAME = username; // To Get Cookie
      GlobalConstants.USERTOKEANkey = usertokean; // To Get Cookie
    }
  }

  console.log(GlobalConstants.USERNAME);
  console.log(GlobalConstants.USERTOKEANkey);

var tokean = {
  username : GlobalConstants.USERNAME,
  tokean : GlobalConstants.USERTOKEANkey

}


    this.UsersService.tokean_check(tokean,'','').subscribe(tokean => {
      
      this.cookieService.username(tokean['username'],GlobalConstants.rember);
      this.cookieService.usertokean(tokean['tokean'],GlobalConstants.rember);


      this._ComponentService.getmenu(this.type,menuid,GlobalConstants.USERNAME,GlobalConstants.USERTOKEANkey).subscribe(res =>{

        this.page=res;
    
        console.log(this.page)
  
        this.pagenumber = this.type === "P"?this.page['parent']['pagesID']['id']:this.page['child']['pagesID']['id'];
    
        GlobalConstants.pageid = this.pagenumber;

      this.locationService.all_info(this.pagenumber,GlobalConstants.USERTOKEANkey,GlobalConstants.USERNAME).then(res => {
        this.device =this.locationService.mydevice;
        console.log(this.device.devicetokean);
        this.pagetokean=this.device.devicetokean;

        GlobalConstants.Devicetokean =this.pagetokean;


var lang=this.cookieService.getCookie('language');
    if(lang === ""){

      this.cookieService.language("EN");
    }else{
      GlobalConstants.language = lang; // To Get Cookie


    }

    this._LanguagegoService.getalllang(this.pagetokean,this.pagenumber.toString()).subscribe(data => {
 //this.langs=data;
 this._MenushareService.updatelang(data);
    });

    this._MenulistService.getmenu(this.pagetokean,this.pagenumber.toString())
.subscribe(data => {
  //this.menus =data;
  this._MenushareService.updatemenu(data);


  });


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
