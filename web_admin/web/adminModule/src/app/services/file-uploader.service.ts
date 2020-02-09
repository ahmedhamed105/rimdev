import * as _ from 'lodash';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { HttpEventType } from '@angular/common/http';
import { Subscription, BehaviorSubject } from 'rxjs';


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

  private _queue: BehaviorSubject<FileQueueObject[]>;
  private _files: FileQueueObject[] = [];

  constructor(private http: HttpClient) {
    this._queue = <BehaviorSubject<FileQueueObject[]>>new BehaviorSubject(this._files);
  }

  // the queue
  public get queue() {
      return this._queue.asObservable();

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

  public uploadAll() {
    // upload all except already successfull or in progress
    _.each(this._files, (queueObj: FileQueueObject) => {
      if (queueObj.isUploadable()) {
        this._upload(queueObj);
      }
    });
  }

  // private functions
  private _addToQueue(file: any,type:string,userid:string) {
    const queueObj = new FileQueueObject(file,type,userid);

    // set the individual object events
    queueObj.upload = () => this._upload(queueObj);
    queueObj.delete = () => this._deleteFromQueuepost(queueObj);
    queueObj.cancel = () => this._cancel(queueObj);
    queueObj.removefromquery = () => this._removeFromQueue(queueObj);

    // push to the queue
    this._files.push(queueObj);
    this._queue.next(this._files);
  }

  private _deleteFromQueuepost(queueObj: FileQueueObject) {

    const form = new FormData();
    form.append('type', queueObj.type);
    form.append('userid', queueObj.userid);

    var urldelete=this.urlremove;

    urldelete=urldelete+queueObj.filename;

    // upload file and report progress
    const req = new HttpRequest('POST', urldelete, form, {
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


  private _removeFromQueue(queueObj: FileQueueObject) {

          _.remove(this._files, queueObj);

  }

  private _upload(queueObj: FileQueueObject) {


    // create form data for file
    const form = new FormData();
    form.append('file', queueObj.file, queueObj.file.name);
    form.append('type', queueObj.type);
    form.append('userid', queueObj.userid);
    // upload file and report progress
    const req = new HttpRequest('POST', this.urladd, form, {
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
    const progress = Math.round(100 * event.loaded / event.total);
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
