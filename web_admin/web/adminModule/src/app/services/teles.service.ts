import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Itele } from '../objects/Itele';
import { Idatastatus } from '../objects/Idatastatus';

@Injectable({
  providedIn: 'root'
})
export class TelesService {
  _urlgetall='http://localhost:8081/Tele/user/';

  _urlgetstall='http://localhost:8081/datastatus/all';
  _urlpostun='http://localhost:8081/Tele/delete';
  _urlpost='http://localhost:8081/Tele/saveorupdate';
  
  constructor(private _http:HttpClient) { }

  getbyuser(userid: number):Observable<Itele[]>{

    var url =this._urlgetall;
    url=url+userid;

    return  this._http.get<Itele[]>(url);
  
    }


    delete(iemail : Itele){
      console.log(iemail);
      return this._http.post<any>(this._urlpostun,iemail);    
      }

    insert(iemail : Itele){
      return this._http.post<any>(this._urlpost,iemail);    
      }


      getstatus(){
        return  this._http.get<Idatastatus[]>(this._urlgetstall);

      }
}
