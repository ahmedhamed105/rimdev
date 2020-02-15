import { Injectable } from '@angular/core';
import { FileUploaderService } from './file-uploader.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FileupdateidService extends FileUploaderService {


  constructor(private _http:HttpClient) {
      super(_http);
  }


}
