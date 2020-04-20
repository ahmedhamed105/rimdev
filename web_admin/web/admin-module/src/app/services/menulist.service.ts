import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class MenulistService {

  constructor(private _http:HttpClient) { }

  
  getmenu(){
    var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.menuurl+"/"+GlobalConstants.language;
    let headers = new HttpHeaders({
      'Cache-Control': 'no-cache',
    'Pragma': 'no-cache',
    'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
       'pageid' :   GlobalConstants.pageid,
      'username':   GlobalConstants.USERNAME,
      'usertokean': GlobalConstants.USERTOKEANkey,
      'devicetokean': GlobalConstants.Devicetokean });
      let options = { headers: headers };
  
    return  this._http.get<[]>(urlall,options);

  }


}
