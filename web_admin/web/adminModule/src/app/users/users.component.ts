import {  Component, EventEmitter, OnInit, Output, ViewChild  } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { UsertypeService } from '../services/usertype.service';
import { formatDate } from '@angular/common';
import { HttpEventType } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FileUploaderService, FileQueueObject } from '../services/file-uploader.service';
import { fileidimages } from '../services/FileIDImages-serv.service';
import { filepassimages } from '../services/file-pass.service';
import { DomSanitizer } from '@angular/platform-browser';

declare var $: any;

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users = [];
  public usertypes = [];
  public useridnumber ;
   format = 'yyyy-MM-dd';
  locale = 'en-US';
  public file: any;


 


  constructor(private sanitizer: DomSanitizer,public passimg: filepassimages,public idimg: fileidimages,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService,private _usertype:UsertypeService){}

  insertform :FormGroup;
  updateform :FormGroup;
  public device ;
  public page_number:number = 5 ;
  public selectuser;
  public type = '1';
  public userid= '2';

  @Output() onCompleteItem = new EventEmitter();

  @ViewChild('fileInput',{static: true}) fileInput;
  @ViewChild('fileInput1',{static: true}) fileInput1;
  queue: Observable<FileQueueObject[]>;
  queueCV: Observable<FileQueueObject[]>;

  ngOnInit() {


    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });


this._usersservice.getall()
.subscribe(data => this.users = data);

console.log(this.users);

this._usertype.getall()
.subscribe(data => this.usertypes = data);

    this.insertform = this.fb.group({
      firstName:['',[Validators.required]],
      middleName: ['' ,[Validators.required]],
      lastname: ['',[Validators.required]],
      birthdate: ['',[Validators.required]],
      passportnumber: ['',[]],
      iDnumber: ['',[]],
      iDimgaes :['',[Validators.required]],
      Paimgaes:['',[Validators.required]],
      usertypeID: this.fb.group({
        id : ['',[Validators.required]]
         })
      });

      this.updateform = this.fb.group({
         id:['',[Validators.required]],
         firstName:['',[Validators.required]],
         middleName: ['' ,[Validators.required]],
         lastname: ['',[Validators.required]],
         birthdate: ['',[Validators.required]],
         useridnumber: [{value: '', disabled: true},[Validators.required]],
         passportnumber: ['',[]],
         iDnumber: ['',[]],
         usertypeID : this.fb.group({
         id : ['',[Validators.required]]
          }),
         });
         
         
         this.queue = this.idimg.queue;
         this.idimg.onCompleteItem = this.completeItem;
         this.idimg.addfilesuser('2','2');

         this.queueCV = this.passimg.queue;
         this.passimg.onCompleteItem = this.completeItem;
         this.passimg.addfilesuser('2','1');
         
  }


  completeItem = (item: FileQueueObject, response: any) => {
   this.onCompleteItem.emit({ item, response });
  }



  addIDimgaes() {
    this.type = '1';
    const fileBrowser = this.fileInput.nativeElement;
    this.idimg.addToQueue(fileBrowser.files,this.type,this.userid);
  }

  addpassimgaes() {
    this.type = '2';
    const fileBrowser = this.fileInput1.nativeElement;
    this.passimg.addToQueue(fileBrowser.files,this.type,this.userid);
  }

  get Paimgaes(){
    return this.insertform.get('Paimgaes');
  }

  get iDimgaes(){
    return this.insertform.get('iDimgaes');
  }
  get iDnumber(){
    return this.insertform.get('iDnumber');
 
   }

  get passportnumber(){
    return this.insertform.get('passportnumber');
 
   }

   get middleName(){
    return this.insertform.get('middleName');
 
   }
   get lastname(){
    return this.insertform.get('lastname');
 
   }
   get birthdate(){
    return this.insertform.get('birthdate');
 
   }


   get firstName(){
    return this.insertform.get('firstName');
 
   }
  
getuser(){
  var id = this.updateform.get('id').value;

  this.selectuser = this.users.filter(x => x.id == id)[0];
  //console.log(this.selectuser.birthdate);
  this.updateform.patchValue({
    firstName: this.selectuser.firstName,
    middleName: this.selectuser.middleName,
    lastname: this.selectuser.lastname,
    birthdate: formatDate(this.selectuser.birthdate, this.format, this.locale),
    passportnumber: this.selectuser.passportnumber,
    useridnumber : this.selectuser.useridnumber,
    iDnumber: this.selectuser.iDnumber,
    usertypeID:{
      id : this.selectuser.usertypeID.id
    }

  });
  //console.log(this.selectcurrency);
}


onUpdate(){
  //console.log(this.insertform.value);
  this._usersservice.insert(this.updateform.value)
  .subscribe(
  data => {
    this.users = data;
  });
  
  this.updateform.reset();
  
  }


  onSubmit(){
  
    //console.log(this.insertform.value);
    this._usersservice.insert(this.insertform.value)
    .subscribe(
      data => {
        this.users = data;

        this.idimg.uploadAll();

        this.idimg.clearQueue();
        
        this.passimg.uploadAll();

        this.passimg.clearQueue();
        
        this.insertform.reset();
    });


    
    }






  }
