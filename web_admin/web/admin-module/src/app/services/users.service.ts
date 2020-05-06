import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  
  constructor(private _http:HttpClient) { }

  getbyurl(url,ip,port):Observable<[]>{
    var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
    let headers = new HttpHeaders({
      'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
      'username':   GlobalConstants.USERNAME,
      'usertokean': GlobalConstants.USERTOKEANkey,
      'pageid': GlobalConstants.pageid,
      'Devicecode': GlobalConstants.PCCODE });
      let options = { headers: headers };
    return  this._http.get<[]>(urlall,options);  
    }

    insertbyurl(object,url,ip,port):Observable<[]>{
      var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language;
      let headers = new HttpHeaders({
        'Cache-Control': 'no-cache',
            'Pragma': 'no-cache',
            'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
            'username':   GlobalConstants.USERNAME,
            'usertokean': GlobalConstants.USERTOKEANkey,
            'pageid': GlobalConstants.pageid,
            'Devicecode': GlobalConstants.PCCODE});
        let options = { headers: headers };
      //  console.log(options)
      return this._http.post<any>(urlall,object,options);    
      }



      getbyvalue(url,value,ip,port):Observable<[]>{
        var urlall=GlobalConstants.protocol+ip+":"+port+url+"/"+GlobalConstants.language+"/";
        urlall=urlall+value;
        let headers = new HttpHeaders({
          'Cache-Control': 'no-cache',
            'Pragma': 'no-cache',
            'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
            'username':   GlobalConstants.USERNAME,
            'usertokean': GlobalConstants.USERTOKEANkey,
            'pageid': GlobalConstants.pageid,
            'Devicecode': GlobalConstants.PCCODE });
          let options = { headers: headers };
        return  this._http.get<[]>(urlall,options);  
        }

        postbythreevalue(url,value1,value2,value3,ip,port):Observable<[]>{
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
            'username':   GlobalConstants.USERNAME,
            'usertokean': GlobalConstants.USERTOKEANkey,
            'pageid': GlobalConstants.pageid,
            'Devicecode': GlobalConstants.PCCODE});
            let options = { headers: headers };
          return  this._http.post<[]>(urlall,file,options);  
          }


        

}
