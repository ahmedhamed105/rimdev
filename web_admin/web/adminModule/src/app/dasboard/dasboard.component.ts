import { Component, OnInit } from '@angular/core';
import { LocationServiceService } from '../services/location-service.service';
import {  Router, ActivatedRoute } from '@angular/router';
import { Idirectory } from '../objects/idirectory';
import { ComponentService } from '../services/component.service';
import { EncryptionService } from '../services/encryption.service';
import { MenulistService } from '../services/menulist.service';
import { CookiesService } from '../services/cookies.service';
import { LanguagegoService } from '../services/languagego.service';
import { GlobalConstants } from '../GlobalConstants';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-dasboard',
  templateUrl: './dasboard.component.html',
  styleUrls: ['./dasboard.component.css']
})
export class DasboardComponent implements OnInit {

  public device ;
  public page : Idirectory;
  public type ;

  public menus =[];
  public langs =[];

  constructor(private UsersService:UsersService,private _LanguagegoService :LanguagegoService,private cookieService: CookiesService,private _MenulistService : MenulistService,private _EncryptionService:EncryptionService,public _ComponentService: ComponentService,private router:Router,private route: ActivatedRoute,private locationService: LocationServiceService) { }

  ngOnInit() {
    this.menus =[];
    this.langs =[];


var username = this.cookieService.getCookie('username');
var usertokean = this.cookieService.getCookie('usertokean');
    if(username === "" || usertokean === ""){
      this.router.navigate(['/login']);
    }else{
      GlobalConstants.USERNAME = username; // To Get Cookie
      GlobalConstants.USERTOKEANkey = usertokean; // To Get Cookie
    }
    

var tokean = {
  username : GlobalConstants.USERNAME,
  tokean : GlobalConstants.USERTOKEANkey

}
    this.UsersService.tokean_check(tokean).subscribe(tokean => {

      this.cookieService.username(tokean['username']);
      this.cookieService.usertokean(tokean['tokean']);


      console.log(GlobalConstants.USERNAME);
      console.log(GlobalConstants.USERTOKEANkey);

    var menuid =this.route.snapshot.paramMap.get("id").toString();

    this.type =this.route.snapshot.paramMap.get("type");

var lang=this.cookieService.getCookie('language');
    if(lang === ""){

      this.cookieService.language("EN");
    }else{
      GlobalConstants.language = lang; // To Get Cookie


    }

    this._LanguagegoService.getalllang().subscribe(data => {
 this.langs=data;
      
    });

    this._MenulistService.getmenu()
.subscribe(data => {
  this.menus =data;
  });



    
    this._ComponentService.getmenu(this.type,menuid).subscribe(res =>{

      this.page=res;
  
      console.log(this.page)
  
      this.locationService.all_info(this.type === "P"?this.page.parent.pagesID.id:this.page.child.pagesID.id).then(res => {
        this.device =this.locationService.mydevice;
        console.log(this.device.devicetokean);
      });
    });


  });
    
  }


  language(code){
this.cookieService.language(code);
  }

}
