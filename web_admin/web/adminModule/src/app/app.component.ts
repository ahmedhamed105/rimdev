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
  
 

  data.forEach((parent,indexp) => {


this.errorDialogService.converttext(parent.parent.pmenu)
.subscribe(data => {
  parent.parent.pmenu = data.returnLang;   
});

var a=  parent.child.sort((a, b) => {
  return a.id -b.id;
});



a.forEach((element,index) => {

  this.errorDialogService.converttext(element.menuname)
  .subscribe(data => {
    element.menuname = data.returnLang;   
  });

});

this.menus.push(parent);
});


  });

  }


  language(code){

    GlobalConstants.language= code;
    this.cookieService.setCookie( 'language', GlobalConstants.language,10,'' ); // To Set Cookie
    location.reload();

  }





}