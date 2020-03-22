import { Component, OnInit } from '@angular/core';
import { MenulistService } from './services/menulist.service';
import { ErrorDialogService } from './services/error-dialog.service';
import { CookiesService } from './services/cookies.service';
import { GlobalConstants } from './GlobalConstants';
import { LanguagegoService } from './services/languagego.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'adminModule';
  public menus =[];
  public langs =[];

  constructor(private _LanguagegoService :LanguagegoService,private cookieService: CookiesService,private _MenulistService : MenulistService,private errorDialogService: ErrorDialogService){}

  ngOnInit() {
    if(this.cookieService.getCookie('language') === ""){

      this.language("EN");
    }else{
      GlobalConstants.language = this.cookieService.getCookie('language'); // To Get Cookie


    }

    this._LanguagegoService.getalllang().subscribe(data => {
 this.langs=data;
      
    });

    this._MenulistService.getmenu()
.subscribe(data => {
  this.menus =data;
  });

  }


  language(code){
    GlobalConstants.language= code;
    this.cookieService.setCookie( 'language', GlobalConstants.language,10,'' ); // To Set Cookie
    location.reload();

  }





}