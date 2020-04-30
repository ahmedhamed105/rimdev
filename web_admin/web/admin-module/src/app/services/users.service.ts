import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  _urlgetall='http://localhost:8081/User/all/EN';
  _urlpost='http://localhost:8081/User/saveorupdate/EN';
  
  constructor(private _http:HttpClient) { }

  getbyurl(url,ip,port,Devicetokean,pageid):Observable<[]>{
    var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
    let headers = new HttpHeaders({
      'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
      'Devicetokean':   Devicetokean,
      'pageid': pageid });
      let options = { headers: headers };
    return  this._http.get<[]>(urlall,options);  
    }

    insertbyurl(object,url,ip,port,Devicetokean,pageid):Observable<[]>{
      var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
      let headers = new HttpHeaders({
        'Cache-Control': 'no-cache',
            'Pragma': 'no-cache',
            'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
        'Devicetokean':   Devicetokean,
        'pageid': pageid });
        let options = { headers: headers };
      //  console.log(options)
      return this._http.post<any>(urlall,object,options);    
      }



      getbyvalue(url,value,ip,port,Devicetokean,pageid):Observable<[]>{
        var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language+"/";
        urlall=urlall+value;
        let headers = new HttpHeaders({
          'Cache-Control': 'no-cache',
            'Pragma': 'no-cache',
            'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
          'Devicetokean':   Devicetokean,
          'pageid': pageid });
          let options = { headers: headers };
        return  this._http.get<[]>(urlall,options);  
        }

        postbythreevalue(url,value1,value2,value3,ip,port,Devicetokean,pageid):Observable<[]>{
          var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
          var file = {
            value1 : value1,
            value2 : value2,
            value3 : value3
          }
          let headers = new HttpHeaders({
            'Cache-Control': 'no-cache',
            'Pragma': 'no-cache',
            'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
            'Devicetokean':   Devicetokean,
            'pageid': pageid });
            let options = { headers: headers };
          return  this._http.post<[]>(urlall,file,options);  
          }


          tokean_check():Observable<[]>{
            var tokeanq = {
              username : GlobalConstants.USERNAME,
              tokean : GlobalConstants.USERTOKEANkey
            
            }
          return this.insertbyurl(tokeanq,GlobalConstants.usertokean,GlobalConstants.ip,GlobalConstants.port,GlobalConstants.Devicetokean,GlobalConstants.pageid);
          }



          public_tokean_check():Observable<[]>{
            var tokeanq = {
              username : 'public',
              tokean : '0'
            
            }
          return this.insertbyurl(tokeanq,GlobalConstants.usertokean,GlobalConstants.ip,GlobalConstants.port,GlobalConstants.Devicetokean,GlobalConstants.pageid);
          }
 

}
