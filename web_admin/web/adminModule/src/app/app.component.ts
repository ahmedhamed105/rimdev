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


  constructor(){}

  ngOnInit() {
  
  }








}