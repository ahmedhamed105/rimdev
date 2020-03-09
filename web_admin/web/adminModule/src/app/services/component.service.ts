import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Icomponent } from '../objects/Icomponent';
import { GlobalConstants } from '../GlobalConstants';
import { Idirectory } from '../objects/idirectory';

@Injectable({
  providedIn: 'root'
})
export class ComponentService {



  constructor(private _http:HttpClient) { }


  getbypage(pageid: number):Observable<Icomponent[]>{

    var url="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+"/Component/page/";
    url=url+pageid;

    return  this._http.get<Icomponent[]>(url);
  
    }


    getmenu(type : String,menuid: String):Observable<Idirectory>{

      var url="http://"+GlobalConstants.ip+":"+GlobalConstants.portuser+"/menu/get/";

      url=url+type+"/"+menuid;

      console.log(url)
  
      return  this._http.get<Idirectory>(url);
    
      }
}
