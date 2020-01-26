import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IDevice } from '../objects/IDevice';
import { Observable } from 'rxjs';
import { pages } from '../objects/pages';

@Injectable({
  providedIn: 'root'
})
export class DevicesService {

  _urlgetall='http://localhost:8081/Device/all';

  _urlgetpages='http://localhost:8081/Device/page/';

  _urlpost='http://localhost:8081/Device/block';
  _urlpostun='http://localhost:8081/Device/unblock';

  constructor(private _http:HttpClient) { }


  getall():Observable<IDevice[]>{

    return  this._http.get<IDevice[]>(this._urlgetall);
  
    }


    block(iDevice : IDevice){
      return this._http.post<any>(this._urlpost,iDevice);    
      }

     unblock(iDevice : IDevice){
        return this._http.post<any>(this._urlpostun,iDevice);    
        }


      getpages(pageid: number):Observable<pages[]>{
        var url =this._urlgetpages;
        url=url+pageid;
        console.log(url);
        return  this._http.get<pages[]>(url);
      
        }
}
