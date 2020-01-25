import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { IDevice } from '../objects/IDevice';

@Injectable({
  providedIn: 'root'
})
export class DeviceServService {

  
  _urlpost='http://192.168.3.76:8081/Device/saveorupdate';
  
  constructor(private _http:HttpClient) { }

  insert(device : IDevice){
   return this._http.post<any>(this._urlpost,device);    
  }
}
