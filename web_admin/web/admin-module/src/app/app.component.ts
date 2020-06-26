import { Component, OnInit } from '@angular/core';
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
    const body = document.getElementsByTagName('body')[0];
   // console.log(window.location.pathname );
   var URL= window.location.pathname 
    if(URL === '/' || URL === '/login'){
      this.login=1;
      this.dashboard=0;
      this.page = 0;
      this.error = 0;
    
    body.classList.add('login-page');
   }
  else if( URL.includes('dashboard')){
    this.login=0;
    this.dashboard=1;
    this.page = 0;
    this.error = 0;
    body.classList.add('sidebar-mini');
 }
 else if( URL.includes('page')) {
  this.login=0;
  this.dashboard=0;
  this.page = 1;
  this.error = 0;
  body.classList.add('sidebar-mini');
}else if(URL.includes('error') || URL.includes('blocked')) {
  this.login=0;
  this.dashboard=0;
  this.page = 0;
  this.error = 1;
  body.classList.add('sidebar-mini');
}else{
  this.login=0;
  this.dashboard=0;
  this.page = 0;
  this.error = 1;
  body.classList.add('sidebar-mini');
}

  }








}