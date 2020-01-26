import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Istatus } from '../objects/Istatus';

@Injectable({
  providedIn: 'root'
})
export class StatusServService {

  _url='http://localhost:8080/Status/all';

  constructor(private _http:HttpClient) { }

  getallstatus():Observable<Istatus[]>{

    return  this._http.get<Istatus[]>(this._url);
  
    }
}
