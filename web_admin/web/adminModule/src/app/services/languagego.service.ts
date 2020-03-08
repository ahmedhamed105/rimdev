import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Ilangsearch } from '../objects/Ilangsearch';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LanguagegoService {

  _urlpost='http://localhost:8083/lang/code';
  
  constructor(private _http:HttpClient) { }

    getlang(search : Ilangsearch){
      return this._http.post<any>(this._urlpost,search);
      }

}
