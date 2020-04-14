import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { GlobalConstants } from '../GlobalConstants';


@Injectable({
  providedIn: 'root'
})
export class LanguagegoService {


  
  constructor(private _http:HttpClient) { }

    getlang(search,Devicetokean,pageid){
      var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.searchlang+"/"+GlobalConstants.language+"/"+search;
      let headers = new HttpHeaders({
        'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
        'Devicetokean':   Devicetokean,
        'pageid': pageid });
        let options = { headers: headers };
      return  this._http.get<{}>(urlall,options);
      }


      getalllang(Devicetokean,pageid){
        var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.langurl+"/"+GlobalConstants.language;       
        let headers = new HttpHeaders({
          'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
          'Devicetokean':   Devicetokean,
          'pageid': pageid });
          let options = { headers: headers };
        return  this._http.get<[]>(urlall,options);
    
      }

}