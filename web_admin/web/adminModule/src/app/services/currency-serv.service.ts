import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Icurrency } from '../objects/Icurrency';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CurrencyServService {
  _urlgetall='http://localhost:8080/Currency/all';
  _urlpost='http://localhost:8080/Currency/saveorupdate';
  
  constructor(private _http:HttpClient) { }

  getall():Observable<Icurrency[]>{

    return  this._http.get<Icurrency[]>(this._urlgetall);
  
    }

    insert(currency : Icurrency){
      return this._http.post<any>(this._urlpost,currency);    
      }


}
