import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IUsertype } from '../objects/iusertype';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsertypeService {
  _urlgetall='http://localhost:8081/Usertype/all/EN';
  _urlpost='http://localhost:8081/Usertype/saveorupdate/EN';
  
  constructor(private _http:HttpClient) { }

  getall():Observable<IUsertype[]>{

    return  this._http.get<IUsertype[]>(this._urlgetall);
  
    }

    insert(currency : IUsertype){
      return this._http.post<any>(this._urlpost,currency);    
      }

}
