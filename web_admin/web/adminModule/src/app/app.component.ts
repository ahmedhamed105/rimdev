import { Component, OnInit } from '@angular/core';
import { MenulistService } from './services/menulist.service';
import { ErrorDialogService } from './services/error-dialog.service';
import { CookiesService } from './services/cookies.service';
import { GlobalConstants } from './GlobalConstants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'adminModule';
  public menus =[];
  public langs =[];

  constructor(private cookieService: CookiesService,public _MenulistService : MenulistService,public errorDialogService: ErrorDialogService){}

  ngOnInit() {
    GlobalConstants.language = this.cookieService.getCookie('language'); // To Get Cookie

    this._MenulistService.getlang().subscribe(data => {
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