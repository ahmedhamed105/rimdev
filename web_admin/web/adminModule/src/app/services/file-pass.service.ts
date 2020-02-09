import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IDevice } from '../objects/IDevice';
import { Observable } from 'rxjs';
import { pages } from '../objects/pages';
import { FileUploaderService } from './file-uploader.service';

@Injectable({
  providedIn: 'root'
})
export class filepassimages extends FileUploaderService {


  constructor(private _http:HttpClient) {
      super(_http);
  }


  
}
