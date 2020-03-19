import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ilangsearch } from '../objects/Ilangsearch';
import { catchError } from 'rxjs/operators';
import { GlobalConstants } from '../GlobalConstants';
import { Ilanguage } from '../objects/Ilanguage';

@Injectable({
  providedIn: 'root'
})
export class LanguagegoService {


  
  constructor(private _http:HttpClient) { }

    getlang(search){
      var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.searchlang+"/"+GlobalConstants.language+"/"+search;

      return  this._http.get<{}>(urlall);
      }


      getalllang(){
        var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.langurl+"/"+GlobalConstants.language;
        return  this._http.get<Ilanguage[]>(urlall);
    
      }

}
