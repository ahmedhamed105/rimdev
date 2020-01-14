import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IFlowType } from '../objects/IFlowType';

@Injectable({
  providedIn: 'root'
})
export class FlowtypeServService {


  _urlgetall='http://localhost:8080/Flow/all';
  _urlpost='http://localhost:8080/Flow/saveorupdate';
  
  constructor(private _http:HttpClient) { }

  getall():Observable<IFlowType[]>{

    return  this._http.get<IFlowType[]>(this._urlgetall);
  
    }

    insert(currency : IFlowType){
      return this._http.post<any>(this._urlpost,currency);    
      }

}
