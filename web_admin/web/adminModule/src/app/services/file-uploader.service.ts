import * as _ from 'lodash';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { HttpEventType } from '@angular/common/http';
import { Subscription, BehaviorSubject, Observable } from 'rxjs';
import { Ifiledownload } from '../objects/Ifiledownload';
import { saveAs } from 'file-saver';


export enum FileQueueStatus {
    Pending,
    Success,
    Error,
    Progress,
    duplicate
  }
  
  
  export class FileQueueObject {
    public file: any;
    public status: FileQueueStatus = FileQueueStatus.Pending;
    public progress: number = 0;
    public request: Subscription = null;
    public response: HttpResponse<any> | HttpErrorResponse = null;
    public filename;
    public type: any;
    public userid: any;

  
    constructor(file: any,type: any,userid: any) {
      this.file = file;
      this.type = type;
      this.userid= userid;
    }

  
    // actions
    public upload = () => { /* set in service */ };
    public cancel = () => { /* set in service */ };
    public delete = () => { /* set in service */ };
    public removefromquery = () => { /* set in service */ };
    public download = () => { /* set in service */ };
  
    // statuses
    public isPending = () => this.status === FileQueueStatus.Pending;
    public isSuccess = () => this.status === FileQueueStatus.Success;
    public isError = () => this.status === FileQueueStatus.Error;
    public isduplicate = () => this.status === FileQueueStatus.duplicate;
    public inProgress = () => this.status === FileQueueStatus.Progress;
    public isUploadable = () => this.status === FileQueueStatus.Pending || this.status === FileQueueStatus.Error;

  }

@Injectable({
  providedIn: 'root'
})
export class FileUploaderService {



  
  public urladd: string = 'http://localhost:8081/file/uploadFile';
  public urlremove: string = 'http://localhost:8081/file/deleteFile/';

  public urldownload: string = 'http://localhost:8081/file/downloadFile/';

  public urlgetfiles: string = 'http://localhost:8081/file/all/';

  private _queue: BehaviorSubject<FileQueueObject[]>;
  private _files: FileQueueObject[] = [];


  constructor(private http: HttpClient) {
    this._queue = <BehaviorSubject<FileQueueObject[]>>new BehaviorSubject(this._files);
    
  }

  // the queue
  public get queue() {
      return this._queue.asObservable();

  }

    // the filelength
    public get filelength() {
      return this._files.length;

  }

  // public events
  public onCompleteItem(queueObj: FileQueueObject, response: any): any {
    return { queueObj, response };
  }

  // public functions
  public addToQueue(data: any,type:string,userid:string) {
    // add file to the queue
    _.each(data, (file: any) => this._addToQueue(file,type,userid));
  }

  public clearQueue() {
    // clear the queue
    this._files = [];
    this._queue.next(this._files);
  }

  public uploadAllinsert(userid) {
    // upload all except already successfull or in progress
    _.each(this._files, (queueObj: FileQueueObject) => {
      if (queueObj.isUploadable()) {
        queueObj.userid = userid;
        this._upload(queueObj);
      }
    });
  }


  public uploadAll() {
    // upload all except already successfull or in progress
    _.each(this._files, (queueObj: FileQueueObject) => {
      if (queueObj.isUploadable()) {
        this._upload(queueObj);
      }
    });
  }

  private _downloadfile(queueObj: FileQueueObject){

    this.download(queueObj.userid, queueObj.type,queueObj.filename).subscribe(data => saveAs(data, queueObj.filename));
  }
  

  public download(userid, filetype,filename) {
var url=this.urldownload+userid+'/'+filetype+'/'+filename;
console.log(url);
    return this.http.get(url, 
      {responseType: 'blob'});
  }


  public addfilesuser(userid:string,filetype:string){

    var url=this.urlgetfiles+userid+'/'+filetype;
      this.http.get<Ifiledownload[]>(url).subscribe(
        
        data => {
          data.forEach(singlefile => {

            var file= {name : singlefile.filesName,size : singlefile.filesSize*1024}
            var queueObj = new FileQueueObject(file,filetype,userid);
           
            queueObj.delete = () => this._deleteFromQueuepost(queueObj);
            queueObj.download = () => this._downloadfile(queueObj);
        
            queueObj.progress = 100;
            queueObj.status = FileQueueStatus.Success;
            queueObj.filename = singlefile.filesName;
               // push to the queue
               this._files.push(queueObj);
               this._queue.next(this._files); 
  
            });
         

        });

      

  }

  // private functions
  private _addToQueue(file: any,type:string,userid:string) {
    var queueObj = new FileQueueObject(file,type,userid);
    queueObj.filename = file.name;
    
    // set the individual object events
    queueObj.upload = () => this._upload(queueObj);
    queueObj.delete = () => this._deleteFromQueuepost(queueObj);
    queueObj.cancel = () => this._cancel(queueObj);
    queueObj.removefromquery = () => this._removeFromQueue(queueObj);
    queueObj.download = () => this._downloadfile(queueObj);
    // push to the queue
    this._files.push(queueObj);
    this._queue.next(this._files);
  }

  private _deleteFromQueuepost(queueObj: FileQueueObject) {

    var form = new FormData();
    form.append('type', queueObj.type);
    form.append('userid', queueObj.userid);

    var urldelete=this.urlremove;

    urldelete=urldelete+queueObj.filename;

    // upload file and report progress
    var req = new HttpRequest('POST', urldelete, form, {
      reportProgress: true,
    });

    // upload file and report progress
    queueObj.request = this.http.request(req).subscribe(
      (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this._uploadProgress(queueObj, event);
        } else if (event instanceof HttpResponse) {
          this._uploadComplete(queueObj, event);
          _.remove(this._files, queueObj);
        }
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          this._uploadFailed(queueObj, err);
        } else {
          // The backend returned an unsuccessful response code.
          this._uploadFailed(queueObj, err);
        }
      }
    );

  
  }
  private downloadfile(queueObj: FileQueueObject) {

    console.log('ahmed');
  return  this.download(2,1,'lKNFM10bdpgita2mcwrr4OHf2ddIzN.jpeg').subscribe(data => saveAs(data, 'lKNFM10bdpgita2mcwrr4OHf2ddIzN.jpeg'));;

}

  private _removeFromQueue(queueObj: FileQueueObject) {

          _.remove(this._files, queueObj);

  }

  private _upload(queueObj: FileQueueObject) {


    // create form data for file
    var form = new FormData();
    form.append('file', queueObj.file, queueObj.filename);
    form.append('type', queueObj.type);
    form.append('userid', queueObj.userid);
    // upload file and report progress
    var req = new HttpRequest('POST', this.urladd, form, {
      reportProgress: true,
    });

    // upload file and report progress
    queueObj.request = this.http.request(req).subscribe(
      (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this._uploadProgress(queueObj, event);
        } else if (event instanceof HttpResponse) {
          this._uploadComplete(queueObj, event);
        }
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          // A client-side or network error occurred. Handle it accordingly.
          this._uploadFailed(queueObj, err);
        } else {
          // The backend returned an unsuccessful response code.
          this._uploadFailed(queueObj, err);
        }
      }
    );

    return queueObj;
  }

  private _cancel(queueObj: FileQueueObject) {
    // update the FileQueueObject as cancelled
    queueObj.request.unsubscribe();
    queueObj.progress = 0;
    queueObj.status = FileQueueStatus.Pending;
    this._queue.next(this._files);
  }

  private _uploadProgress(queueObj: FileQueueObject, event: any) {
    // update the FileQueueObject with the current progress
    var progress = Math.round(100 * event.loaded / event.total);
    queueObj.progress = progress;
    queueObj.status = FileQueueStatus.Progress;
    this._queue.next(this._files);
  }

  private _uploadComplete(queueObj: FileQueueObject, response: HttpResponse<any>) {
    // update the FileQueueObject as completed
    queueObj.progress = 100;
    queueObj.status = FileQueueStatus.Success;
    queueObj.response = response;
    queueObj.filename = response.body.fileName;
    this._queue.next(this._files);
    this.onCompleteItem(queueObj, response.body);
  }

  private _uploadFailed(queueObj: FileQueueObject, response: HttpErrorResponse) {
    // update the FileQueueObject as errored
    queueObj.progress = 0;

    console.log(response);

    if(response.status == 409){
   queueObj.status = FileQueueStatus.duplicate;
    }else{
    queueObj.status = FileQueueStatus.Error;
    }
    queueObj.response = response;
    this._queue.next(this._files);
  }

}