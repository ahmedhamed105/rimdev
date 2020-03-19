import * as _ from 'lodash';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { HttpEventType } from '@angular/common/http';
import { Subscription, BehaviorSubject, Observable } from 'rxjs';
import { Ifiledownload } from '../objects/Ifiledownload';
import { saveAs } from 'file-saver';
import { UsersService } from '../services/users.service';
import { Data } from '@angular/router';
import { GlobalConstants } from '../GlobalConstants';


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
    public fileid;
    public type: any;
    public index: any;
    public pageid:any;
    public parentid:any;
    public componentid:any;
    public insertserv:any;
    public insertparmeter:any;
    public object:any;

  
    constructor(file: any,type: any,index: any,pageid:number,parentid:number,componentid:number) {
      this.file = file;
      this.type = type;
      this.index = index;
      this.pageid=pageid;
      this.parentid=parentid;
      this.componentid=componentid;
    }

  
    // actions
    public upload = (object) => { /* set in service */ };
    public cancel = () => { /* set in service */ };
    public delete = (deleteserv) => { /* set in service */ };
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


  private _queue: BehaviorSubject<FileQueueObject[]> []=[];
  private _files: FileQueueObject[][]=[];



  constructor(private http: HttpClient,private _usersservice:UsersService) {
    
  }

  // the queue
  public  queue(index) {

    console.log(index)
    if(this._queue[index] == undefined){
      console.log("new")
      this._files[index]=[];
      this._queue[index] = <BehaviorSubject<FileQueueObject[]>>new BehaviorSubject(this._files[index]);
    }

      return this._queue[index].asObservable();

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
  public addToQueue(data: any,type:string,index:number,pageid:number,parentid:number,componentid:number,insert,parameter,ip,port) {
    // add file to the queue
    _.each(data, (file: any) => this._addToQueue(file,type,index,pageid,parentid,componentid,insert,parameter,ip,port));
  }

  public clearQueue() {
    // clear the queue
    _.each(this._files, (queueObj1: FileQueueObject[]) => {

      _.each(queueObj1, (queueObj: FileQueueObject) => {

      //  console.log(queueObj.index)
        
          this._files[queueObj.index] = [];
          this._queue[queueObj.index].next(this._files[queueObj.index]);
        
            });
          });
   

  }

  public uploadAllinsert(object,ip,port) {
    // upload all except already successfull or in progress
    var i = 0 ;
    _.each(this._files, (queueObj1: FileQueueObject[]) => {

      _.each(queueObj1, (queueObj: FileQueueObject) => {

        if (queueObj.isUploadable()) { 
          //  queueObj.userid = userid;
            this._upload(queueObj,object,ip,port);
            i++;
          }
        
            });
          });
  }



  private _downloadfile(queueObj: FileQueueObject,ip,port){

    this.download(queueObj.fileid,ip,port).subscribe(data => saveAs(data, queueObj.filename));
  }
  

  public download(fileid,ip,port) {

    var urlall=GlobalConstants.protocol+ip+":"+port+GlobalConstants.urldownload+"/"+GlobalConstants.language;
    var url=urlall+"/"+fileid;
console.log(url);
    return this.http.get(url, 
      {responseType: 'blob'});
  }


  public addfilesuser(url,userid,delteobject,ip,port): any{

   this._usersservice.getbyvalue(url,userid,ip,port).subscribe(
        
        data => {
          
          data.forEach(singlefile => {

            var file= {name : singlefile['filesuploadID']['filesName'],size : singlefile['filesuploadID']['filesSize']*1024}
         

        console.log(singlefile['filesuploadID']['filesName'])
        console.log(singlefile['componentID']['id'])

        //    var queueObj = new FileQueueObject(file,'type','index','pageid','parentid','componentid');
        var queueObj = new FileQueueObject(file,'type',singlefile['componentID']['id'],0,0,singlefile['componentID']['id']);
            queueObj.delete = (deleteserv) => this._deleteFromQueuepost(queueObj,deleteserv,ip,port);
            queueObj.download = () => this._downloadfile(queueObj,ip,port);
        
            queueObj.progress = 100;
            queueObj.status = FileQueueStatus.Success;
            queueObj.filename = singlefile['filesuploadID']['filesName'];
            queueObj.fileid = singlefile['filesuploadID']['id'];
            queueObj.componentid = singlefile['componentID']['id'];
            queueObj.object = delteobject;
               // push to the queue
               if(this._files[singlefile['componentID']['id']] === undefined){
                this._files[singlefile['componentID']['id']] = [];
               }
               if(this._queue[singlefile['componentID']['id']] === undefined){

                this._queue[singlefile['componentID']['id']] = <BehaviorSubject<FileQueueObject[]>>new BehaviorSubject(this._files[singlefile['componentID']['id']]);
               }
           
               this._files[singlefile['componentID']['id']].push(queueObj);
               this._queue[singlefile['componentID']['id']].next(this._files[singlefile['componentID']['id']]); 
  
            });
           console.log(this.queue(36))
            return  this.queue(36);
        });

      

  }

  // private functions
  private _addToQueue(file: any,type:string,index:number,pageid:number,parentid:number,componentid:number,insertserv,inspara,ip,port) {

console.log(index)

    var queueObj = new FileQueueObject(file,type,index,pageid,parentid,componentid);
    queueObj.filename = file.name;
    queueObj.index = index;
    queueObj.insertserv = insertserv;
    queueObj.insertparmeter = inspara;
    // set the individual object events
    queueObj.upload = (object) => this._upload(queueObj,object,ip,port);
    queueObj.delete = (deleteserv) => this._deleteFromQueuepost(queueObj,deleteserv,ip,port);
    queueObj.cancel = () => this._cancel(queueObj);
    queueObj.removefromquery = () => this._removeFromQueue(queueObj);
    queueObj.download = () => this._downloadfile(queueObj,ip,port);
    // push to the queue
    this._files[index].push(queueObj);
    this._queue[index].next(this._files[index]);
  }

  private _deleteFromQueuepost(queueObj: FileQueueObject,deleteserv,ip,port) {

    var form = new FormData();
    form.append('fileid', queueObj.fileid);
    form.append('object', queueObj.object);
    form.append('component', queueObj.componentid);

  var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+deleteserv+"/"+GlobalConstants.language;


    // upload file and report progress
    var req = new HttpRequest('POST', urlall, form, {
      reportProgress: true,
    });

    // upload file and report progress
    queueObj.request = this.http.request(req).subscribe(
      (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this._uploadProgress(queueObj, event);
        } else if (event instanceof HttpResponse) {
          this._uploadComplete(queueObj, event,1,null,null,ip,port);
          _.remove(this._files[queueObj.index], queueObj);
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

          _.remove(this._files[queueObj.index], queueObj);

  }

  private _upload(queueObj: FileQueueObject,object,ip,port) {

    console.log(object);

    console.log(queueObj.insertserv,queueObj.insertparmeter,queueObj.componentid);


    // create form data for file
    var form = new FormData();
    form.append('file', queueObj.file, queueObj.filename);
    form.append('pageid', queueObj.pageid);
    form.append('parentid', queueObj.parentid);
    form.append('componentid', queueObj.componentid);
  //  form.append('userid', queueObj.userid);
    // upload file and report progress
    var urlall=GlobalConstants.protocol+ip+":"+port+GlobalConstants.urladd+"/"+GlobalConstants.language;

    var req = new HttpRequest('POST', urlall, form, {
      reportProgress: true,
    });

    // upload file and report progress
    queueObj.request = this.http.request(req).subscribe(
      (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this._uploadProgress(queueObj, event);
        } else if (event instanceof HttpResponse) {
          this._uploadComplete(queueObj, event,0,object,queueObj.componentid,ip,port);
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
    this._queue[queueObj.index].next(this._files[queueObj.index]);
  }

  private _uploadProgress(queueObj: FileQueueObject, event: any) {
    // update the FileQueueObject with the current progress
    var progress = Math.round(100 * event.loaded / event.total);
    queueObj.progress = progress;
    queueObj.status = FileQueueStatus.Progress;
    this._queue[queueObj.index].next(this._files[queueObj.index]);
  }

  private _uploadComplete(queueObj: FileQueueObject, response: HttpResponse<any>,para,object,compid,ip,port) {
    // update the FileQueueObject as completed
if(para === 0){
  console.log("insert");
  this._usersservice.postbythreevalue(queueObj.insertserv,object,response.body.id,compid,ip,port).subscribe(data => {
console.log(data);
queueObj.progress = 100;
queueObj.status = FileQueueStatus.Success;
queueObj.response = response;
queueObj.filename = response.body.filesName;
queueObj.fileid= response.body.id;
this._queue[queueObj.index].next(this._files[queueObj.index]);
this.onCompleteItem(queueObj, response.body);

  });
}


 
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
    this._queue[queueObj.index].next(this._files[queueObj.index]);
  }

}
