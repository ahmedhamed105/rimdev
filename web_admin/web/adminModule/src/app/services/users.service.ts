import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  _urlgetall='http://localhost:8081/User/all/EN';
  _urlpost='http://localhost:8081/User/saveorupdate/EN';
  
  constructor(private _http:HttpClient) { }

  getbyurl(url,ip,port):Observable<[]>{
    var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
    return  this._http.get<[]>(urlall);  
    }

    insertbyurl(object,url,ip,port):Observable<[]>{
      var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
      return this._http.post<any>(urlall,object);    
      }



      getbyvalue(url,value,ip,port):Observable<[]>{
        var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language+"/";
        urlall=urlall+value;
        return  this._http.get<[]>(urlall);  
        }

        postbythreevalue(url,value1,value2,value3,ip,port):Observable<[]>{
          var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
          var file = {
            value1 : value1,
            value2 : value2,
            value3 : value3
          }
          return  this._http.post<[]>(urlall,file);  
          }


           tokean_check(object):Observable<[]>{
          return this.insertbyurl(object,GlobalConstants.usertokean,GlobalConstants.ip,GlobalConstants.port);
          }
 

}
