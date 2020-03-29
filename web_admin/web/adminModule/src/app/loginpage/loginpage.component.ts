import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Validators, FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { ComponentService } from '../services/component.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Icolumdef } from '../objects/Icolumdef';
import { Observable,of  } from 'rxjs';
import { FileQueueObject, FileUploaderService } from '../services/file-uploader.service';
import { GlobalConstants } from '../GlobalConstants';
import * as _ from 'lodash';
import { formatDate } from '@angular/common';
import { PasswordtableComponent } from '../passwordtable/passwordtable.component';
import { EncryptionService } from '../services/encryption.service';
import { MenulistService } from '../services/menulist.service';
import { CookiesService } from '../services/cookies.service';
import { LanguagegoService } from '../services/languagego.service';
import { MatCheckboxChange } from '@angular/material/checkbox';

@Component({
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {


  @Output() onCompleteItem = new EventEmitter();
  queue: Observable<FileQueueObject[]> []=[];


  public objects = [[]];
  public components = [];
  public arraystatic = [];
  public type ;
  public isfile;

  constructor(private _LanguagegoService :LanguagegoService,private cookieService: CookiesService,private _MenulistService : MenulistService,private _EncryptionService:EncryptionService,private fileupload: FileUploaderService,private router:Router,private route: ActivatedRoute,public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup []=[];
  tmpform :FormGroup;
  public device ;
  gridOptions:GridOptions []=[];
  rowData: any []=[];

 columnDefs: Icolumdef[] []=[];
 public column:Icolumdef[] = [];
 
 public file =[] ;
 public dates =[] ;
 public passwords =[] ;
 public checkbox =[] ;
 passwordIsValid = false;

 public menus =[];
 public langs =[];

  ngOnInit(){

    this.menus =[];
    this.langs =[];

    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };


    this.cookieService.checkboxrember('0');

    var lang = this.cookieService.getCookie('language') ;

    if(lang === ""){

      this.cookieService.language("EN");
    }else{
      GlobalConstants.language = lang; // To Get Cookie


    }

    this._LanguagegoService.getalllang().subscribe(data => {
 this.langs=data;
      
    });

    this._MenulistService.getmenu()
.subscribe(data => {
  this.menus =data;
  });




    this.isfile =false;
    this.file= [];
    this.dates =[] ;


    var pageid =12;



    this.locationService.all_info(pageid).then(res => {
      this.device =this.locationService.mydevice;
      console.log(this.device.devicetokean);
    });


   
    
    this._ComponentService.getbypage(pageid).subscribe(res =>{

      res.forEach((parent,indexp) => {

        this.column = [];


        this.components.push(parent);


        if(parent.child != null && parent.parent.parentType === 'form'){

          this.insertform.push(new FormGroup({}));

          this.insertform[parent.parent.id] = this.fb.group({
          });
  
  
  

        
        var a=  parent.child.sort((a, b) => {
          return a.comp.seqNum -b.comp.seqNum;
        });

        

      a.forEach((element,index) => {
     
        var parentin=indexp*a.length;


        if(element.comp.ctype == 'button'){


        }else{



          this.createItem(element.comp.name,element.comp.groupname,element.comp.crequired,element.comp.cpattern,element.comp.patterndesgin,parent.parent.id);
        
          if(element.comp.ctype == 'input' &&  element.input.inputtypeID.itype == 'file'){
             
             this.isfile = true;

             this.file.push(element.comp.name);

           this.queue[element.comp.id] = this.fileupload.queue(element.comp.id);
           this.fileupload.onCompleteItem = this.completeItem;

          }else if(element.comp.ctype == 'input' &&  element.input.inputtypeID.itype == 'date'){
             
            this.dates.push(element.comp.name);

     

         }else if(element.comp.ctype == 'input' &&  element.input.inputtypeID.itype == 'password'){
             
          this.passwords.push(element.comp.name);

   

       }

          
        
        
        }
        


if(element.comp.ctype == 'select'){

        if(element.select.arrayObject === 1){
          if(element.select.webService != undefined){

            this._usersservice.getbyurl(element.select.webService,parent.parent.comIP,parent.parent.comport)
            .subscribe(data => {this.objects[index+parentin] = data;
           //  console.log(index+indexp);
          //  console.log(element.select.webService)
        });
           }

           }else{
            this.objects[index+parentin]=[];
             var res = element.select.selectValue.replace('[', '').replace(']', '').split(",");
             var res1 = element.select.selectDisplay.replace('[', '').replace(']', '').split(",");
             res1.forEach((element, index1) => {
               let  prearray = {
                 key : element,
                 value :res[index1]
               };
               this.objects[index+parentin].push(prearray);
             });
       
            
       
           }

          }




   
       
});


}else if(parent.child != null && parent.parent.parentType === 'table'){


  this.gridOptions[parent.parent.id]= <GridOptions>{};
  this.gridOptions[parent.parent.id].frameworkComponents = { "selRenderer" : UsertypedropdownComponent,"passRenderer" : PasswordtableComponent };

  var a=  parent.child.sort((a, b) => {
    return a.comp.seqNum -b.comp.seqNum;
  });



  var b : Icolumdef = {
    headerName : "select row",
    field : null,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    fielddisable: false,
    ip:"",
    port:"",
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: true,
    cellRenderer: ""
  };
  this.column.push(b);
 // this.columnDefs[parent.parent.id][0] = b;
  

a.forEach((element,index) => {



  var parentin=indexp*a.length;

if(element.comp.ctype === 'label'){

  var b : Icolumdef = {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname+'.'+element.comp.name,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    fielddisable: element.comp.disable === 1 ? true:false,
    ip:parent.parent.comIP,
    port:parent.parent.comport,
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: ""
  };
 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);

 

}else if(element.comp.ctype === 'input'){

  var b : Icolumdef= {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname+'.'+element.comp.name,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    fielddisable: element.comp.disable === 1 ? true:false,
    ip:parent.parent.comIP,
    port:parent.parent.comport,
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: true,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer : ""
  };
 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);
  
 

}else if(element.comp.ctype === 'password'){

  var b : Icolumdef= {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname,
    Serv: element.select.webService,
    selectDisplay:element.select.selectDisplay,
    selectValue:element.select.selectValue,
    fieldgroup: element.comp.groupname === undefined? 0 : 1,
    groupname : element.comp.groupname === undefined?null:element.comp.groupname,
    fielddisable: element.comp.disable === 1 ? true:false,
    ip:parent.parent.comIP,
    port:parent.parent.comport,
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer : "passRenderer"
  };
 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);
  
 

}else if(element.comp.ctype === 'select'){

  console.log(element.comp.groupname === undefined? element.comp.name:element.comp.groupname)

  var b : Icolumdef= {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname,
    Serv: element.select.webService,
    selectDisplay:element.select.selectDisplay,
    selectValue:element.select.selectValue,
    fieldgroup: element.comp.groupname === undefined? 0 : 1,
    groupname : element.comp.groupname === undefined?null:element.comp.groupname,
    fielddisable: element.comp.disable === 1 ? true:false,
    ip:parent.parent.comIP,
    port:parent.parent.comport,
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: "selRenderer"
  };
 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);


}else{

}





 
});

this.columnDefs[parent.parent.id]=this.column;


}

if(parent.parent.firstmethod === undefined){

}else{
  console.log("go");
  this.rowData[parent.parent.id] =  this._usersservice.getbyurl(parent.parent.firstmethod,parent.parent.comIP,parent.parent.comport)

}

});


    });
    

 
if(this.isfile){
  this.fileupload.clearQueue();
}
 



  }



   
completeItem = (item: FileQueueObject, response: any) => {
  this.onCompleteItem.emit({ item, response });
 }


 addfiles($event,name,index,pageid,parentid,compid,insert,parameter,ip,port) {

  const fileBrowser = $event.target;
  

  if (fileBrowser.files[0].size > GlobalConstants.max_size) {
    alert('size is alrger than ');
     return false;
 }

 if (!GlobalConstants.allowed_types.includes(fileBrowser.files[0].type)) {
   alert('Only Images are allowed ( JPG | PNG )');
     return false;
 }

 
  this.fileupload.addToQueue(fileBrowser.files,name,index,pageid,parentid,compid,insert,parameter,ip,port);
}


createItem(child,group,req,pat,dpattern,formindex) {
if(group != null){
  if(this.insertform[formindex].get(group)== null){
    this.insertform[formindex].addControl(group, new FormGroup({}));
  }
  this.tmpform = this.insertform[formindex].get(group) as FormGroup;

  if(req === 1 && pat === 1){
    
    this.tmpform.addControl(child,  new FormControl('', [Validators.required,Validators.pattern(dpattern)]));
  }else if(req === 1 && pat === 0){
    this.tmpform.addControl(child,  new FormControl('', [Validators.required]));
  }else if(req === 0 && pat === 1){
    this.tmpform.addControl(child,  new FormControl('', [Validators.pattern(dpattern)]));
  }else{
    this.tmpform.addControl(child,  new FormControl(''));
  }
}else{


  if(req === 1 && pat === 1){
    
    this.insertform[formindex].addControl(child,  new FormControl('', [Validators.required,Validators.pattern(dpattern)]));
  }else if(req === 1 && pat === 0){
    this.insertform[formindex].addControl(child,  new FormControl('', [Validators.required]));
  }else if(req === 0 && pat === 1){
    this.insertform[formindex].addControl(child,  new FormControl('', [Validators.pattern(dpattern)]));
  }else{
    this.insertform[formindex].addControl(child,  new FormControl(''));
  }

}


 
  }



onSubmit(form,serv,related,relcom,ip,port,routingInd,routingLoc){

  

 if( this.passwords.length > 0){

  this.passwords.forEach(element => {


    form.get(element).setValue(this._EncryptionService.encypttext(form.get(element).value.trim()));
  });


  console.log(form.value)
}
     this._usersservice.insertbyurl(form.value,serv,ip,port).subscribe(data => {

    console.log(data)



        // get table number 
        if(related === 'table'){
        this.rowData[relcom]= of(data) ;
        }
      
      if(this.isfile){
        this.fileupload.uploadAllinsert(data[data.length-1],ip,port)
        this.fileupload.clearQueue();

      } 



      form.reset();

      if(related === 'login'){
        if(data['username'] == undefined || data['tokean'] == undefined){
          this.cookieService.username('',0);
          this.cookieService.usertokean('',0);

        }else{
         
          this.cookieService.username(data['username'],GlobalConstants.rember);
          this.cookieService.usertokean(data['tokean'],GlobalConstants.rember);
        }
  
       }

       if(routingInd === 1){
        this.router.navigate([routingLoc]);
       }

    });
    

 
 
}


resetform(form,serv,related,relcom){
  if(this.isfile){
    this.fileupload.clearQueue();

  } 
  form.reset();
}


passwordValid(event) {
  this.passwordIsValid = event;
}


checked(event:MatCheckboxChange){
  console.log(event.checked);
  if(event.checked === true){
    this.cookieService.checkboxrember('1');
  }else{
    this.cookieService.checkboxrember('0');
  }

}


}
