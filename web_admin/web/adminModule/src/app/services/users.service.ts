import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Iuser } from '../objects/Iuser';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  _urlgetall='http://localhost:8081/User/all';
  _urlpost='http://localhost:8081/User/saveorupdate';
  _urlpostfile='http://localhost:8081/file/uploadFile';
  
  constructor(private _http:HttpClient) { }

  getall():Observable<Iuser[]>{

    return  this._http.get<Iuser[]>(this._urlgetall);
  
    }

    insert(user : Iuser){
      console.log(user);
      return this._http.post<any>(this._urlpost,user);    
      }




 

}
