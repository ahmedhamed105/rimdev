import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Icurrency } from '../objects/Icurrency';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyServService {
  _urlgetall='http://192.168.3.76:8080/Currency/all';
  _urlpost='http://192.168.3.76:8080/Currency/saveorupdate';
  
  constructor(private _http:HttpClient) { }

  getall():Observable<Icurrency[]>{

    return  this._http.get<Icurrency[]>(this._urlgetall);
  
    }

    insert(currency : Icurrency){
      return this._http.post<any>(this._urlpost,currency);    
      }


}
