import { Component, OnInit } from '@angular/core';
import { MenulistService } from './services/menulist.service';
import { ErrorDialogService } from './services/error-dialog.service';
import { CookiesService } from './services/cookies.service';
import { GlobalConstants } from './GlobalConstants';
import { LanguagegoService } from './services/languagego.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'adminModule';

  public login=0;
  public dashboard = 0;
  public page =0;
  public error =0;

  constructor(private router: ActivatedRoute){}

  ngOnInit() {
    console.log(window.location.pathname );
   var URL= window.location.pathname 
    if(URL === '/' || URL === '/login'){
      this.login=1;
      this.dashboard=0;
      this.page = 0;
      this.error = 0;
   }
  else if( URL.includes('dashboard')){
    this.login=0;
    this.dashboard=1;
    this.page = 0;
    this.error = 0;
 }
 else if( URL.includes('page') || URL.includes('error') || URL.includes('blocked')) {
  this.login=0;
  this.dashboard=0;
  this.page = 1;
  this.error = 0;
}else if(URL.includes('error') || URL.includes('blocked')) {
  this.login=0;
  this.dashboard=0;
  this.page = 0;
  this.error = 1;
}else{
  this.login=0;
  this.dashboard=0;
  this.page = 0;
  this.error = 1;
}

  }








}