import * as _ from 'lodash';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders, HttpRequest, HttpResponse } from '@angular/common/http';
import { HttpEventType } from '@angular/common/http';
import { Subscription, BehaviorSubject, Observable } from 'rxjs';
import { saveAs } from 'file-saver';
import { UsersService } from '../services/users.service';
import { Data } from '@angular/router';
import { GlobalConstants } from '../GlobalConstants';
import { ErrorDialogService } from './error-dialog.service';


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
    public insertip:any;
    public insertport:any;
    public deleteserv:any;
    public deleteip:any;
    public deleteport:any;

  
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
    public delete = (serv,ip,port) => { /* set in service */ };
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



  constructor(private _ErrorDialogService :ErrorDialogService,private http: HttpClient,private _usersservice:UsersService) {
    
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
    public  filelength(index) {
      return this._files[index].length;

  }

  // public events
  public onCompleteItem(queueObj: FileQueueObject, response: any): any {
    return { queueObj, response };
  }

  // public functions
  public addToQueue(data: any,type:string,index:number,pageid:number,parentid:number,componentid:number,insertserv,parameter,insertip,insertport,deleteserv,deleteip,deleteport,filecount,maxfilesize,fileCounterr,fileSizeerr,filetypes,fileTypeerror) {
    // add file to the queue

    var filescount=data.length+ this.filelength(index);

    if(filecount === undefined){
      filecount = 0;
    }
  
    if(filescount > filecount){
      let map = new Map();
      map.set("filecount",filecount); 
      alert(this._ErrorDialogService.formaterror(fileCounterr,map,null,null,null));
      return false;
  
    }


    if(filetypes.length <= 0){
      let map = new Map();
      map.set("wrongtype",'unsupported');
      alert(this._ErrorDialogService.formaterror(fileTypeerror,map,null,null,null));
      return false;
    }else{
  
      var count=0;
  
      filetypes.forEach((type) => {
        if (type.fmime === data[0].type) {
          count ++;
        }
      });
  
      if(count <= 0){
        let map = new Map();
        map.set("wrongtype",data[0].type); 
        alert(this._ErrorDialogService.formaterror(fileTypeerror,map,null,null,null));
        return false;
      }
  
    }
  
    if(maxfilesize === undefined){
      maxfilesize = 0;
    }
  
    if (data[0].size > maxfilesize) {
      let map = new Map();
      map.set("maxfilesize",maxfilesize); 
      alert(this._ErrorDialogService.formaterror(fileSizeerr,map,null,null,null));
       return false;
   }


    _.each(data, (file: any) => this._addToQueue(file,type,index,pageid,parentid,componentid,insertserv,parameter,insertip,insertport,deleteserv,deleteip,deleteport));
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

  public uploadAllinsert(object) {

    
    // upload all except already successfull or in progress
    var i = 0 ;
    _.each(this._files, (queueObj1: FileQueueObject[]) => {

      _.each(queueObj1, (queueObj: FileQueueObject) => {

        if (queueObj.isUploadable()) { 
          //  queueObj.userid = userid;
            this._upload(queueObj,object);
            i++;
          }
        
            });
          });
  }



  private _downloadfile(queueObj: FileQueueObject){

    this.download(queueObj).subscribe(data => saveAs(data, queueObj.filename));
  }
  

  public download(queueObj) {

    var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.urldownload+"/"+GlobalConstants.language;
    var url=urlall+"/"+queueObj.fileid;
console.log(url);
let headers = new HttpHeaders({
  'Cache-Control': 'no-cache',
    'Pragma': 'no-cache',
    'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
    'username':   GlobalConstants.USERNAME,
    'usertokean': GlobalConstants.USERTOKEANkey,
    'pageid': GlobalConstants.pageid,
    'Devicecode': GlobalConstants.PCCODE });
  let options = {  reportProgress: true ,responseType: 'blob' as  'blob',headers: headers };
    return this.http.get(url, options);
  }


  public addfilesuser(data): any{
          console.log(data)
          
          data.forEach(singlefile => {

            console.log(singlefile)

            var file= {name : singlefile['filesuploadID']['filesName'],size : singlefile['filesuploadID']['filesSize']*1024}
         

        console.log(singlefile['filesuploadID']['filesName'])
        console.log(singlefile['componentID']['id'])

        //    var queueObj = new FileQueueObject(file,'type','index','pageid','parentid','componentid');
        var queueObj = new FileQueueObject(file,'type',singlefile['componentID']['id'],0,0,singlefile['componentID']['id']);
            queueObj.delete = (serv,ip,port) => this._deleteFromQueuepost(queueObj,serv,ip,port);
            queueObj.download = () => this._downloadfile(queueObj);
        
            queueObj.progress = 100;
            queueObj.status = FileQueueStatus.Success;
            queueObj.filename = singlefile['filesuploadID']['filesName'];
            queueObj.fileid = singlefile['filesuploadID']['id'];
            queueObj.componentid = singlefile['componentID']['id'];
            queueObj.object = singlefile["userloginID"]["id"];

               // push to the queue
               if(this._files[queueObj.componentid] === undefined){
                this._files[queueObj.componentid] = [];
               }
               if(this._queue[queueObj.componentid] === undefined){

                this._queue[queueObj.componentid] = <BehaviorSubject<FileQueueObject[]>>new BehaviorSubject(queueObj.componentid);
               }
           
               this._files[queueObj.componentid].push(queueObj);
               this._queue[queueObj.componentid].next(this._files[queueObj.componentid]); 
  
            });

  }

  // private functions
  private _addToQueue(file: any,type:string,index:number,pageid:number,parentid:number,componentid:number,insertserv,inspara,insertip,insertport,deleteserv,deleteip,deleteport) {

    var queueObj = new FileQueueObject(file,type,index,pageid,parentid,componentid);
    queueObj.filename = file.name;
    queueObj.index = index;
    queueObj.insertserv = insertserv;
    queueObj.insertparmeter = inspara;
    queueObj.insertip = insertip
    queueObj.insertport = insertport
    queueObj.deleteserv = deleteserv;
    queueObj.deleteip = deleteip;
    queueObj.deleteport = deleteport;
    // set the individual object events
    queueObj.upload = (object) => this._upload(queueObj,object);
    queueObj.delete = (serv,ip,port) => this._deleteFromQueuepost(queueObj,serv,ip,port);
    queueObj.cancel = () => this._cancel(queueObj);
    queueObj.removefromquery = () => this._removeFromQueue(queueObj);
    queueObj.download = () => this._downloadfile(queueObj);
    // push to the queue
    this._files[index].push(queueObj);
    this._queue[index].next(this._files[index]);
  }

  private _deleteFromQueuepost(queueObj: FileQueueObject,serv,ip,port) {

    var form = new FormData();
    form.append('fileid', queueObj.fileid);
    form.append('object', queueObj.object);
    form.append('component', queueObj.componentid);
    queueObj.deleteserv =serv;
    queueObj.deleteip=ip;
    queueObj.deleteport=port;


  var urlall=GlobalConstants.protocol+queueObj.deleteip+":"+queueObj.deleteport+queueObj.deleteserv+"/"+GlobalConstants.language;
 
  let headers = new HttpHeaders({
    'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
      'username':   GlobalConstants.USERNAME,
      'usertokean': GlobalConstants.USERTOKEANkey,
      'pageid': GlobalConstants.pageid,
      'Devicecode': GlobalConstants.PCCODE});
    let options = {  reportProgress: true,headers: headers };

    // upload file and report progress
    var req = new HttpRequest('POST', urlall, form, options);

    // upload file and report progress
    queueObj.request = this.http.request(req).subscribe(
      (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this._uploadProgress(queueObj, event);
        } else if (event instanceof HttpResponse) {
          this._uploadComplete(queueObj, event,1,null,null);
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

  private _upload(queueObj: FileQueueObject,object) {


    // create form data for file
    var form = new FormData();
    form.append('file', queueObj.file, queueObj.filename);
    form.append('pageid', queueObj.pageid);
    form.append('parentid', queueObj.parentid);
    form.append('componentid', queueObj.componentid);
    
    // upload file and report progress
    var urlall=GlobalConstants.protocol+GlobalConstants.ip+":"+GlobalConstants.port+GlobalConstants.urladd+"/"+GlobalConstants.language;
    let headers = new HttpHeaders({
      'Cache-Control': 'no-cache',
      'Pragma': 'no-cache',
      'Expires': 'Sat, 01 Jan 2000 00:00:00 GMT',
      'username':   GlobalConstants.USERNAME,
      'usertokean': GlobalConstants.USERTOKEANkey,
      'pageid': GlobalConstants.pageid,
      'Devicecode': GlobalConstants.PCCODE });
      let options = {  reportProgress: true,headers: headers };

    var req = new HttpRequest('POST', urlall, form, options);

    // upload file and report progress
    queueObj.request = this.http.request(req).subscribe(
      (event: any) => {
        if (event.type === HttpEventType.UploadProgress) {
          this._uploadProgress(queueObj, event);
        } else if (event instanceof HttpResponse) {
          this._uploadComplete(queueObj, event,0,object,queueObj.componentid);
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

  private _uploadComplete(queueObj: FileQueueObject, response: HttpResponse<any>,para,object,compid) {
    // update the FileQueueObject as completed
if(para === 0){
  console.log("insert");
  this._usersservice.postfiles(queueObj.insertserv,object,compid,response.body,queueObj.insertip,queueObj.insertport).subscribe(data => {
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
