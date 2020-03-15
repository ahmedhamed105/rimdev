import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Iuser } from '../objects/Iuser';
import { Observable } from 'rxjs';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  _urlgetall='http://localhost:8081/User/all/EN';
  _urlpost='http://localhost:8081/User/saveorupdate/EN';
  
  constructor(private _http:HttpClient) { }

  getbyurl(url):Observable<[]>{
    var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+url+"/"+GlobalConstants.language;
    return  this._http.get<[]>(urlall);  
    }

    insertbyurl(object,url):Observable<[]>{
      var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+url+"/"+GlobalConstants.language;
      return this._http.post<any>(urlall,object);    
      }


      getbyvalue(url,value):Observable<[]>{
        var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+url+"/"+GlobalConstants.language+"/";
        urlall=urlall+value;
        return  this._http.get<[]>(urlall);  
        }

        postbythreevalue(url,value1,value2,value3):Observable<[]>{
          var urlall="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+url+"/"+GlobalConstants.language;
          var file = {
            value1 : value1,
            value2 : value2,
            value3 : value3
          }
          return  this._http.post<[]>(urlall,file);  
          }





    getall():Observable<[]>{
      return  this._http.get<[]>(this._urlgetall);  
      }

    insert(user : Iuser){
      console.log(user);
      return this._http.post<any>(this._urlpost,user);    
      }




 

}
