import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Iemail } from '../objects/Iemail';

@Injectable({
  providedIn: 'root'
})
export class EmailsService {

  _urlgetall='http://localhost:8081/Email/user/';
  _urlpostun='http://localhost:8081/Email/delete';
  _urlpost='http://localhost:8081/Email/saveorupdate';
  
  constructor(private _http:HttpClient) { }

  getemails(userid: number):Observable<Iemail[]>{

    var url =this._urlgetall;
    url=url+userid;

    return  this._http.get<Iemail[]>(url);
  
    }


    delete(iemail : Iemail){
      console.log(iemail);
      return this._http.post<any>(this._urlpostun,iemail);    
      }

    insert(iemail : Iemail){
      return this._http.post<any>(this._urlpost,iemail);    
      }
}
