import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class MenulistService {

  constructor(private _http:HttpClient) { }

  
  getmenu(Devicetokean,pageid){
    var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.menuurl+"/"+GlobalConstants.language;
    let headers = new HttpHeaders({
      'Devicetokean':   Devicetokean,
      'pageid': pageid });
      let options = { headers: headers };
  
    return  this._http.get<[]>(urlall,options);

  }


}
