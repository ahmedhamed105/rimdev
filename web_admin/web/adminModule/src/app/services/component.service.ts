import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Icomponent } from '../objects/Icomponent';

@Injectable({
  providedIn: 'root'
})
export class ComponentService {

  _urlgetall='http://localhost:8081/Component/page/';

  constructor(private _http:HttpClient) { }


  getbypage(pageid: number):Observable<Icomponent[]>{

    var url =this._urlgetall;
    url=url+pageid;

    return  this._http.get<Icomponent[]>(url);
  
    }
}
