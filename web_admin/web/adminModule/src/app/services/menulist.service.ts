import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalConstants } from '../GlobalConstants';
import { Imenu } from '../objects/imenu';
import { Ilanguage } from '../objects/Ilanguage';

@Injectable({
  providedIn: 'root'
})
export class MenulistService {

  constructor(private _http:HttpClient) { }

  
  getmenu(){
    var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+GlobalConstants.menuurl+"/"+GlobalConstants.language;
    return  this._http.get<Imenu[]>(urlall);

  }


}
