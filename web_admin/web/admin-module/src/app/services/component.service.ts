import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Icomponent } from '../objects/Icomponent';
import { GlobalConstants } from '../GlobalConstants';

@Injectable({
  providedIn: 'root'
})
export class ComponentService {



  constructor(private _http:HttpClient) { }

  


  getbypage():Observable<Icomponent[]>{

    var url=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.Componentpage+GlobalConstants.language;
    let headers = new HttpHeaders({
      'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
      'username':   GlobalConstants.USERNAME,
      'usertokean': GlobalConstants.USERTOKEANkey,
      'pageid': GlobalConstants.pageid,
      'Devicecode': GlobalConstants.PCCODE });
      let options = { headers: headers };
    return  this._http.get<Icomponent[]>(url,options);
    }



    getimage(fileid):any{

      var url=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.renderimage+GlobalConstants.language;
     url=url+"/"+fileid
     
      let headers = new HttpHeaders({
        'Cache-Control': 'no-cache',
        'Pragma': 'no-cache',
        'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
        'username':   GlobalConstants.USERNAME,
        'usertokean': GlobalConstants.USERTOKEANkey,
        'pageid': GlobalConstants.pageid,
        'Devicecode': GlobalConstants.PCCODE
       });
        let options = { headers: headers,responseType: 'blob' as  'blob' };
       return this._http.get(url, options);
      }


    getbackground():any{

      var url=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.background+GlobalConstants.language;
      let headers = new HttpHeaders({
        'Cache-Control': 'no-cache',
        'Pragma': 'no-cache',
        'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
        'username':   GlobalConstants.USERNAME,
        'usertokean': GlobalConstants.USERTOKEANkey,
        'pageid': GlobalConstants.pageid,
        'Devicecode': GlobalConstants.PCCODE
       });
        let options = { headers: headers,responseType: 'blob' as  'blob' };
       return this._http.get(url, options);
      }


    getmenu(type,menuid):Observable<any>{

      var url=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.pagefrommenu+GlobalConstants.language+"/";

      url=url+type+'/'+menuid;

      console.log(GlobalConstants.language)

      let headers = new HttpHeaders({
        'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
      'username':   GlobalConstants.USERNAME,
      'usertokean': GlobalConstants.USERTOKEANkey,
      'pageid': GlobalConstants.pageid,
      'Devicecode': GlobalConstants.PCCODE });
        let options = { headers: headers };
  
      return  this._http.get<any>(url,options);
    
      }
}
