import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Iuser } from '../objects/Iuser';
import { Observable } from 'rxjs';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  _urlgetall='http://localhost:8081/User/all';
  _urlpost='http://localhost:8081/User/saveorupdate';
  
  constructor(private _http:HttpClient) { }

  getbyurl(url):Observable<[]>{
    var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+url;
    return  this._http.get<[]>(urlall);  
    }

    insertbyurl(object,url):Observable<[]>{
      var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+url;
      return this._http.post<any>(urlall,object);    
      }


    getall():Observable<[]>{
      return  this._http.get<[]>(this._urlgetall);  
      }

    insert(user : Iuser){
      console.log(user);
      return this._http.post<any>(this._urlpost,user);    
      }




 

}
