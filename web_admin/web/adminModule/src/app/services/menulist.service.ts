import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalConstants } from '../GlobalConstants';
import { Imenu } from '../objects/imenu';

@Injectable({
  providedIn: 'root'
})
export class MenulistService {

  constructor(private _http:HttpClient) { }

  
  getmenu(){
    var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+GlobalConstants.menuurl;
    return  this._http.get<Imenu[]>(urlall);

  }
}
