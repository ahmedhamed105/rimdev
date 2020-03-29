import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class MenulistService {

  constructor(private _http:HttpClient) { }

  
  getmenu(){
    var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.menuurl+"/"+GlobalConstants.language;
    return  this._http.get<[]>(urlall);

  }


}
