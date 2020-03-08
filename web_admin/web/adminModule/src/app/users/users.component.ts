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
import { FileupdatepassportService } from '../services/fileupdatepassport.service';
import { FileupdateidService } from '../services/fileupdateid.service';
import { FileValidate } from '../Validation/FileValidate';
import { map, defaultIfEmpty, finalize } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';

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
  public errormessage;


 


  constructor(private route: ActivatedRoute,public fileupdateid :FileupdateidService,public fileupdatepassport :FileupdatepassportService,public passimg: filepassimages,public idimg: fileidimages,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService,private _usertype:UsertypeService){}

  insertform :FormGroup;
  updateform :FormGroup;
  public device ;
  public page_number;
  public selectuser;
  public type = '1';
  public userid= '2';

   max_size = 20971520;
   allowed_types = ['image/png', 'image/jpeg'];
   max_height = 15200;
   max_width = 25600;




  @Output() onCompleteItem = new EventEmitter();

  @ViewChild('fileInput',{static: true}) fileInput;
  @ViewChild('fileInput1',{static: true}) fileInput1;
  queue: Observable<FileQueueObject[]>;
  queueCV: Observable<FileQueueObject[]>;


  @ViewChild('fileInput2',{static: true}) fileInput2;
  @ViewChild('fileInput3',{static: true}) fileInput3;
  queueupdate: Observable<FileQueueObject[]>;
  queueCVupdate: Observable<FileQueueObject[]>;
   count:number=0;
  count1:number=0;
  ngOnInit() {
    this.page_number =this.route.snapshot.paramMap.get("id");


    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });


this._usersservice.getall()
.subscribe(data => this.users = data);

//console.log(this.users);

this._usertype.getall()
.subscribe(data => this.usertypes = data);


      
this.queue = this.idimg.queue;
this.idimg.onCompleteItem = this.completeItem;

this.queueCV = this.passimg.queue;
this.passimg.onCompleteItem = this.completeItem;


this.queueupdate = this.fileupdateid.queue;
this.fileupdateid.onCompleteItem = this.completeItem;

this.queueCVupdate = this.fileupdatepassport.queue;
this.fileupdatepassport.onCompleteItem = this.completeItem;



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
         
   
         
  }


  completeItem = (item: FileQueueObject, response: any) => {
   this.onCompleteItem.emit({ item, response });
  }



  addIDimgaes() {
    this.type = '1';
    const fileBrowser = this.fileInput.nativeElement;
    

    if (fileBrowser.files[0].size > this.max_size) {
      alert('size is alrger than ');
       return false;
   }

   if (!this.allowed_types.includes(fileBrowser.files[0].type)) {
     alert('Only Images are allowed ( JPG | PNG )');
       return false;
   }

   
    this.idimg.addToQueue(fileBrowser.files,this.type,this.userid);
  }

  addpassimgaes() {
    this.type = '2';
    const fileBrowser = this.fileInput1.nativeElement;
    

    if (fileBrowser.files[0].size > this.max_size) {
      alert('size is alrger than ');
       return false;
   }

   if (!this.allowed_types.includes(fileBrowser.files[0].type)) {
     alert('Only Images are allowed ( JPG | PNG )');
       return false;
   }

    this.passimg.addToQueue(fileBrowser.files,this.type,this.userid);
  }


  addupdatepassimgaes() {
    if(this.selectuser == null){
      this.errormessage ='please select user First';

    }else{
      this.type = '2';
      const fileBrowser = this.fileInput2.nativeElement;



      if (fileBrowser.files[0].size > this.max_size) {
         alert('size is alrger than ');
          return false;
      }

      if (!this.allowed_types.includes(fileBrowser.files[0].type)) {
        alert('Only Images are allowed ( JPG | PNG )');
          return false;
      }


    
      this.fileupdatepassport.addToQueue(fileBrowser.files,this.type,this.selectuser.id);
    }

  }

  addupdateIDimgaes() {
    if(this.selectuser == null){
      this.errormessage ='please select user First';

    }else{
    this.type = '1';
    const fileBrowser = this.fileInput3.nativeElement;


    if (fileBrowser.files[0].size > this.max_size) {
      alert('size is alrger than ');
       return false;
   }

   if (!this.allowed_types.includes(fileBrowser.files[0].type)) {
     alert('Only Images are allowed ( JPG | PNG )');
       return false;
   }


    this.fileupdateid.addToQueue(fileBrowser.files,this.type,this.selectuser.id);
    }
  }


  get Paupdateimgaes(){
    return this.updateform.get('Paupdateimgaes');
  }

  get iDupdateimgaes(){
    return this.updateform.get('iDupdateimgaes');
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
  this.fileupdateid.clearQueue();
  this.fileupdatepassport.clearQueue();
  var id = this.updateform.get('id').value;

  this.selectuser = this.users.filter(x => x.id == id)[0];

  this.fileupdateid.addfilesuser(this.selectuser.id,'1');
  this.fileupdatepassport.addfilesuser(this.selectuser.id,'2');



  this.count=0;
  this.count1=0;


  this.queueCVupdate
.subscribe(
    (data) => {
      console.log('file '+data.length)
      this.count += data.length;
      this.queueupdate
      .subscribe(
          (data) => {
            console.log('file2 '+data.length)
            this.count1 += data.length;

          }
      );

    }
);



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

  
}


onUpdate(){


if(this.count > 0 && this.count1 >0){
  //console.log(this.insertform.value);
  this._usersservice.insert(this.updateform.value)
  .subscribe(
  data => {
    console.log(data)
  });
  this.updateform.reset();

}else{

  alert("enter files");
}


  
  }


  onSubmit(){
  
    //console.log(this.insertform.value);
    this._usersservice.insert(this.insertform.value)
    .subscribe(
      data => {
        let insertuser = data;

        this.idimg.uploadAllinsert(insertuser.id);

        this.idimg.clearQueue();
        
        this.passimg.uploadAllinsert(insertuser.id);

        this.passimg.clearQueue();
        
        this.insertform.reset();
    });


    
    }






  }
