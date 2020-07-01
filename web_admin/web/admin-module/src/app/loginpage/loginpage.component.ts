import { Component, EventEmitter, OnInit, Output, ElementRef, AfterViewInit, Renderer2, ChangeDetectionStrategy } from '@angular/core';
import { Validators, FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { ComponentService } from '../services/component.service';
import { Icolumdef } from '../objects/Icolumdef';
import { Observable,of  } from 'rxjs';
import { FileQueueObject, FileUploaderService } from '../services/file-uploader.service';
import { GlobalConstants } from '../GlobalConstants';
import { PasswordtableComponent } from '../passwordtable/passwordtable.component';
import { EncryptionService } from '../services/encryption.service';
import { MenulistService } from '../services/menulist.service';
import { CookiesService } from '../services/cookies.service';
import { LanguagegoService } from '../services/languagego.service';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MenushareService } from '../share_data/menushare.service';
import { SpinnerService } from '../services/spinner.service';

@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-loginpage',
  templateUrl: './loginpage.component.html',
  styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {


  @Output() onCompleteItem = new EventEmitter();
  queue: Observable<FileQueueObject[]> []=[];

  
 public background ;


 

 public objects = [[]];
 public components = [];
 public arraystatic = [];
 public placeholders = [];

 public allcompoent = new Map<any, any>();
 public page ;
 public pagenumber =12;
 public type ;
 public isfile;
 private domLayout;

 constructor(private errordis :ErrorDialogService,private spinnerService: SpinnerService,private renderer:Renderer2,private elementRef: ElementRef,private _MenushareService:MenushareService,private _LanguagegoService :LanguagegoService,private cookieService: CookiesService,private _MenulistService : MenulistService,private _EncryptionService:EncryptionService,private fileupload: FileUploaderService,public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

 insertform :FormGroup []=[];
 tmpform :FormGroup;
 public device ;
 gridOptions:GridOptions []=[];
 rowData: any []=[];
 tablepagination:any  []=[];
columnDefs: Icolumdef[] []=[];
public column:Icolumdef[] = [];

public file :any [][] =[] ;
public filetmp :any [] =[] ;
public dates :any [][] =[] ;
public datestmp :any [] =[] ;
public passwords :any [][]=[] ;
public tpasswords :any []=[] ;
public passwordstable :any [][] =[] ;
public tpasswordstable :any [] =[] ;
passwordIsValid = false;


 createImage(url: any) {
  
    this.background = url;
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-image', "url('"+this.background+"')");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-repeat', "no-repeat");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-size', "cover");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-position', "center");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-attachment', "fixed");
  
}

createImageFromBlob(image: Blob) {
  let reader = new FileReader();
  reader.addEventListener("load", () => {
    this.background = reader.result;
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-image', "url('"+this.background+"')");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-repeat', "no-repeat");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-size', "cover");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-position', "center");
    this.renderer.setStyle(this.elementRef.nativeElement.ownerDocument.body,'background-attachment', "fixed");
  }, false);
  if (image) {
    reader.readAsDataURL(image);
  }
}


ngOnInit(){


   
  this.cookieService.checkboxrember('1');

  GlobalConstants.rember = this.cookieService.getCookie('rember');
if( GlobalConstants.rember === '1'){
  GlobalConstants.USERNAME = this.cookieService.getCookie('username');
  GlobalConstants.USERTOKEANkey = this.cookieService.getCookie('usertokean');
}



  var lang = this.cookieService.getCookie('language') ;

  if(lang === ""){

    this.cookieService.language("EN");
  }else{
    GlobalConstants.language = lang; // To Get Cookie


  }




  this.isfile =false;

  GlobalConstants.pageid = this.pagenumber.toString();
  


  this.locationService.all_info().then(res => {

    this.device =this.locationService.mydevice;

    this.cookieService.username(this.device['username'],GlobalConstants.rember);
    this.cookieService.usertokean(this.device['usertokean'],GlobalConstants.rember);

    this._MenushareService.updatelang(undefined); 
    this._MenushareService.updatemenu(undefined);

    this._MenushareService.updateuser(undefined);
    this._MenushareService.updateapp(undefined);
    this._MenushareService.updatenotif(undefined);


    this._ComponentService.getbackground().subscribe(background =>{

      if(background.size === 0){
        this.createImage('assets/img/avatar04.png');
      }else{
        this.createImageFromBlob(background);
      }

  //this.page=res;

 // //console.log(this.page)

    

  this._ComponentService.getbypage().subscribe(res =>{

  // //console.log(res);
    

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

      var parentin=element.comp.id;
      this.allcompoent.set(parentin,[element.comp.name,element.comp.groupname,element.comp.parentGroup]);

      this.placeholders[parentin]= element.comp.ccode;

      if(element.comp.fieldEncry === 1){

        this.tpasswords.push(element);

      }

    


      if(element.comp.ctype == 'button'){


      }else{



        this.createItem(element.comp.name,element.comp.parentGroup,element.comp.groupname,element.comp.crequired,element.comp.cpattern,element.comp.patterndesgin,parent.parent.id);
      
        if(element.comp.ctype == 'input' &&  element.input.inputtypeID.itype == 'file'){
           
           this.isfile = true;

           this.filetmp.push(element);

         this.queue[element.comp.id] = this.fileupload.queue(element.comp.id);
         this.fileupload.onCompleteItem = this.completeItem;

        }else if(element.comp.ctype == 'input' &&  element.input.inputtypeID.itype == 'date'){
           
          this.datestmp.push(element);

   

       }else if(element.comp.ctype == 'input' &&  element.input.inputtypeID.itype == 'password'){
           
        

 

     }

        
      
      
      }
      


if(element.comp.ctype == 'select'){

      if(element.select.arrayObject === 1){
        if(element.select.webService != undefined){
         
          this._usersservice.getbyurl(element.select.webService,element.select.comIP,element.select.comport)
          .subscribe(data => {this.objects[parentin] = data;
          
        
      });
         }

         }else{
          this.objects[parentin]=[];
           var res = element.select.selectValue.replace('[', '').replace(']', '').split(",");
           var res1 = element.select.selectDisplay.replace('[', '').replace(']', '').split(",");
           res1.forEach((element, index1) => {
             let  prearray = {
               key : element,
               value :res[index1]
             };
             this.objects[parentin].push(prearray);
           });
     
          
     
         }

        }




 
     
});


this.passwords[parent.parent.id]=this.tpasswords;
this.dates[parent.parent.id]=this.datestmp;
this.file[parent.parent.id]=this.filetmp;


}else if(parent.child != null && parent.parent.parentType === 'table'){

this.tablepagination[parent.parent.id]=parent.parent.pagination;
this.gridOptions[parent.parent.id]= <GridOptions>{};
this.gridOptions[parent.parent.id].frameworkComponents = { "selRenderer" : UsertypedropdownComponent,"passRenderer" : PasswordtableComponent };
this.gridOptions[parent.parent.id].rowHeight = 100;
this.domLayout = 'autoHeight';
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
  parentgroup: "",
  fielddisable: false,
  disabled : false,
  ip:"",
  port:"",
  formnum:0,
  sortable: true, 
  filter: true, 
  editable: false,      
  resizable: true,
  checkboxSelection: true,
  cellRenderer: "",
  hide : false
  
};
this.column.push(b);
// this.columnDefs[parent.parent.id][0] = b;


a.forEach((element,index) => {

var b : Icolumdef;


if(element.comp.fieldEncry === 1){

  this.tpasswordstable.push(element);

}

this.passwordstable[parent.parent.id]=this.tpasswordstable;



if(element.comp.ctype === 'label'){


if(element.comp.parentGroup === undefined){
  
  b  = {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname+'.'+element.comp.name,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: "",
    hide : element.comp.visible === 1 ? false:true
  };

}else{

  b = {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.parentGroup+'.'+element.comp.name:element.comp.parentGroup+'.'+element.comp.groupname+'.'+element.comp.name,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: "",
    hide : element.comp.visible === 1 ? false:true
  };

}


// this.columnDefs[parent.parent.id][index+1]= b;
this.column.push(b);



}else if(element.comp.ctype === 'input'){




if(element.comp.parentGroup === undefined){

  b = {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname+'.'+element.comp.name,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: true,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer : "",
    hide : element.comp.visible === 1 ? false:true
  };
 
}else{


  b = {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.parentGroup+'.'+element.comp.name:element.comp.parentGroup+'.'+element.comp.groupname+'.'+element.comp.name,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: true,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer : "",
    hide : element.comp.visible === 1 ? false:true
  };
  
}


// this.columnDefs[parent.parent.id][index+1]= b;
this.column.push(b);



}else if(element.comp.ctype === 'password'){



if(element.comp.parentGroup === undefined){

  b = {
    headerName : element.comp.ccode,
    field :  element.comp.name,
    Serv: element.select.webService,
    selectDisplay:element.select.selectDisplay,
    selectValue:element.select.selectValue,
    fieldgroup: element.comp.groupname === undefined? 0 : 1,
    groupname : element.comp.groupname === undefined?null:element.comp.groupname,
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer : "passRenderer",
    hide : element.comp.visible === 1 ? false:true
  };
  

}else{

  b = {
    headerName : element.comp.ccode,
    field : element.comp.name,
    Serv: element.select.webService,
    selectDisplay:element.select.selectDisplay,
    selectValue:element.select.selectValue,
    fieldgroup: element.comp.groupname === undefined? 0 : 1,
    groupname : element.comp.groupname === undefined?null:element.comp.groupname,
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer : "passRenderer",
    hide : element.comp.visible === 1 ? false:true
  };
  
}

// this.columnDefs[parent.parent.id][index+1]= b;
this.column.push(b);



}else if(element.comp.ctype === 'select'){

if(element.comp.parentGroup === undefined){

  b = {
    headerName : element.comp.ccode,
    field :  element.comp.name,
    Serv: element.select.webService,
    selectDisplay:element.select.selectDisplay,
    selectValue:element.select.selectValue,
    fieldgroup: element.comp.groupname === undefined? 0 : 1,
    groupname : element.comp.groupname === undefined?null:element.comp.groupname,
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: "selRenderer",
    hide : element.comp.visible === 1 ? false:true
  };

}else{

  b = {
    headerName : element.comp.ccode,
    field : element.comp.name,
    Serv: element.select.webService,
    selectDisplay:element.select.selectDisplay,
    selectValue:element.select.selectValue,
    fieldgroup: element.comp.groupname === undefined? 0 : 1,
    groupname : element.comp.groupname === undefined?null:element.comp.groupname,
    parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
    fielddisable: element.comp.disable === 1 ? true:false,
    disabled : element.comp.disable === 1 ? true:false,
    ip:element.select.comIP,
    port:element.select.comport,
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: "selRenderer",
    hide : element.comp.visible === 1 ? false:true
  };

  
}

////console.log(element.comp.groupname === undefined? element.comp.name:element.comp.groupname)


// this.columnDefs[parent.parent.id][index+1]= b;
this.column.push(b);


}else{

}






});

this.columnDefs[parent.parent.id]=this.column;


}

if(parent.parent.firstmethod === undefined){

}else{

this.rowData[parent.parent.id] =  this._usersservice.getbyurl(parent.parent.firstmethod,parent.parent.comIP,parent.parent.comport);

}

});


  });

  
});

});

if(this.isfile){
this.fileupload.clearQueue();
}


     

}

 



   
completeItem = (item: FileQueueObject, response: any) => {
  this.onCompleteItem.emit({ item, response });
 }


 addfiles($event,name,index,pageid,parentid,compid,insertserv,parameter,insertip,insertport,deleteserv,deleteip,deleteport,filecount,maxfilesize,fileCounterr,fileSizeerr,filetypes,fileTypeerror) {

  const fileBrowser = $event.target;

 
  this.fileupload.addToQueue(fileBrowser.files,name,index,pageid,parentid,compid,insertserv,parameter,insertip,insertport,deleteserv,deleteip,deleteport,filecount,maxfilesize,fileCounterr,fileSizeerr,filetypes,fileTypeerror);
}


createItem(child,pargroup,group,req,pat,dpattern,formindex) {
  if(pargroup != null){
// parent group have value

if(group != null){

  if(this.insertform[formindex].get(pargroup) == null){
    this.insertform[formindex].addControl(pargroup, new FormGroup({}));
  }

  this.tmpform = this.insertform[formindex].get(pargroup) as FormGroup;

  if(this.tmpform.get(group)== null){
    this.tmpform.addControl(group, new FormGroup({}));
  }

  this.tmpform = this.insertform[formindex].get(pargroup).get(group) as FormGroup;

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


  if(this.insertform[formindex].get(pargroup) == null){
    this.insertform[formindex].addControl(pargroup, new FormGroup({}));;
  }

  this.tmpform = this.insertform[formindex].get(pargroup) as FormGroup;



  if(req === 1 && pat === 1){
    
    this.tmpform.addControl(child,  new FormControl('', [Validators.required,Validators.pattern(dpattern)]));
  }else if(req === 1 && pat === 0){
    this.tmpform.addControl(child,  new FormControl('', [Validators.required]));
  }else if(req === 0 && pat === 1){
    this.tmpform.addControl(child,  new FormControl('', [Validators.pattern(dpattern)]));
  }else{
    this.tmpform.addControl(child,  new FormControl(''));
  }

}


  }else{
// parent group null value


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


 
  }



  onSubmit(event,rel,form,parentid,type){
    
    //console.log(rel.length)
    //console.log(rel) 

    if( rel != undefined || rel.length > 0 ){
      rel.forEach((comp,indexp) => {
     var value='';
        if(type === 'button'){
          if( this.passwords[parentid].length > 0){

            this.passwords[parentid].forEach(element => {
              var value;
              var nodetmp;
            
              if(element.comp.parentGroup != null && element.comp.groupname != null){
                nodetmp = form.get(element.comp.parentGroup).get(element.comp.groupname).get(element.comp.name).value.trim();
                value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                form.get(element.comp.parentGroup).get(element.comp.groupname).get(element.comp.name).setValue(value);
              }else if(element.comp.parentGroup != null && element.comp.groupname == null){
                nodetmp = form.get(element.comp.parentGroup).get(element.comp.name).value.trim();
                value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                form.get(element.comp.parentGroup).get(element.comp.name).setValue(value);
               }else if(element.comp.parentGroup  == null && element.comp.groupname != null){
                nodetmp = form.get(element.comp.groupname).get(element.comp.name).value.trim();
                value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                form.get(element.comp.groupname).get(element.comp.name).setValue(value);
              }else{
                nodetmp = form.get(element.comp.name).value.trim();
                value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                form.get(element.comp.name).setValue(value);
              }
          
            });
          
          }

        value = form.value;

       

        if(value != undefined){
          // alert(comp.relatedComponent +' '+ comp.service +' '+ comp.relatedParent +' '+ comp.parServ );
          
             if(comp.relatedComponent != null && comp.service != null && comp.service != undefined){
               this._usersservice.insertbyurl(value,comp.service,comp.comIP,comp.comport)
             .subscribe(data => {   
               if(comp.datatype === 'A'){
                 this.objects[comp.relatedComponent] = data;   
               }else if(comp.datatype === 'NP'){
                   this.placeholders[comp.relatedComponent] = data['text'];                   
               }else if(comp.datatype === 'TP'){
                   relatcom.setValue(data['text']);
                   this.placeholders[comp.relatedComponent] = data['text'];                   
               }  else if(comp.datatype === 'TN'){
                   relatcom.setValue(data['text']);
               }else if(comp.datatype === 'N'){
 
               } 
               this.customactioncode(data);

               if(comp.alertAfter === 1){
                
                this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,data));
               }
   
               if(comp.resetind === 1){
                this.insertform[comp.resetParent].reset();
                         if(this.isfile){
                          this.fileupload.clearQueue();
                         }  
               }
         
               if(comp.routingInd === 1 ){
                 setTimeout(() => {
                   window.location.replace(comp.routingLoc);
                }, 2000);
         
               }

                          
             });
                 }
                 
             

                 if(comp.relatedParent != undefined && comp.parServ != undefined){
                  
                  this._usersservice.insertbyurl(value,comp.parServ,comp.comIPpar,comp.comportpar).subscribe(
                    data => {
                      
                      this.customactioncode(data);

                      if(this.isfile){
                        this.fileupload.uploadAllinsert(value)
                        this.fileupload.clearQueue();
                      } 
                      this.rowData[comp.relatedParent] = of(data);

                      if(comp.alertAfter === 1){
                        this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,data));
                       }
           
                       if(comp.resetind === 1){
                         this.insertform[comp.resetParent].reset();
                         if(this.isfile){
                          this.fileupload.clearQueue();
                         } 
                       }
                 
                       if(comp.routingInd === 1 ){
                         setTimeout(() => {
                           window.location.replace(comp.routingLoc);
                        }, 2000);
                 
                       }
                    });
                   
                  }

 
                  if(comp.relatedComponent == undefined && comp.parServ == undefined && comp.relatedParent == undefined && comp.parServ == undefined && comp.resetind === 1 && comp.resetParent != undefined){
                   
                   
                    if(comp.alertAfter === 1){
                      this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,null));
                     }

                     if(comp.resetind === 1){
                      this.insertform[comp.resetParent].reset();
                      if(this.isfile){
                       this.fileupload.clearQueue();
                      } 
                    }
                 
               
                     if(comp.routingInd === 1 ){
                       setTimeout(() => {
                         window.location.replace(comp.routingLoc);
                      }, 2000);
               
                     }
                 
                  }
 
 
           }else{
            
            if(comp.alertAfter === 1){
              this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,null));
             }
 
             if(comp.resetind === 1){
               form.reset();
               if(this.isfile){
             //    this.fileupload.clearQueue();
               } 
             }
       
             if(comp.routingInd === 1 ){
               setTimeout(() => {
                 window.location.replace(comp.routingLoc);
              }, 2000);
       
             }  
 
             }

    

        
        }else{
        value= event.target.value;
        var relatcom=form.get(this.allcompoent.get(comp.relatedComponent)[0]);
        


          if(value.length > 0){
            // alert(comp.relatedComponent +' '+ comp.service +' '+ comp.relatedParent +' '+ comp.parServ );
         
               if(comp.relatedComponent != undefined && comp.service != undefined){
                
                 this._usersservice.insertbyurl(value,comp.service,comp.comIP,comp.comport)
               .subscribe(data => {   
                 if(comp.datatype === 'A'){
                   this.objects[comp.relatedComponent] = data;   
                 }else if(comp.datatype === 'NP'){
                     this.placeholders[comp.relatedComponent] = data['text'];                   
                 }else if(comp.datatype === 'TP'){
                     relatcom.setValue(data['text']);
                     this.placeholders[comp.relatedComponent] = data['text'];                   
                 }  else if(comp.datatype === 'TN'){
                     relatcom.setValue(data['text']);
                 }else if(comp.datatype === 'N'){
   
                 } 

                 this.customactioncode(data);

                 if(comp.alertAfter === 1){
                  this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,data));
                 }
     
                 if(comp.resetind === 1){
                  relatcom.setValue('');
                 }
           
                 if(comp.routingInd === 1 ){
                   setTimeout(() => {
                     window.location.replace(comp.routingLoc);
                  }, 2000);
           
                 }

                
                 
               });
                   }

                   if(comp.relatedParent != undefined && comp.parServ != undefined){

                    this._usersservice.insertbyurl(value,comp.parServ,comp.comIPpar,comp.comportpar).subscribe(
                      data => {
                       
                        this.rowData[comp.relatedParent] = of(data);

                        if(comp.alertAfter === 1){
                          this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,data));
                         }
             
                         if(comp.resetind === 1){
                          relatcom.setValue('');
                         }
                   
                         if(comp.routingInd === 1 ){
                           setTimeout(() => {
                             window.location.replace(comp.routingLoc);
                          }, 2000);
                   
                         }
                        
                      });
                     
                    }
   
                    if(comp.relatedComponent == undefined && comp.parServ == undefined && comp.relatedParent == undefined && comp.parServ == undefined && comp.resetind === 1 && comp.resetParent != undefined){
                      relatcom.setValue('');
                    } 
   
             }else{
               if(comp.relatedComponent != undefined && comp.emptyServ != undefined){
                 this._usersservice.getbyurl(comp.emptyServ,comp.comIP,comp.comport)
                 .subscribe(data => {
                   
                  this.objects[comp.relatedComponent] = data;
                  if(comp.alertAfter === 1){
                    this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,data));
                   }
       
                   if(comp.resetind === 1){
                    relatcom.setValue('');
                   }
             
                   if(comp.routingInd === 1 ){
                     setTimeout(() => {
                       window.location.replace(comp.routingLoc);
                    }, 2000);
             
                   }

                 });
               }
   
                 if(comp.relatedParent != undefined && comp.parServ != undefined){
                 this._usersservice.getbyurl(comp.relparentServempty,comp.comIPpar,comp.comportpar).subscribe(
                  data => {
                   
                    this.rowData[comp.relatedParent] = of(data);

                    if(comp.alertAfter === 1){
                      this.errorDialogService.display_sucess(this.errorDialogService.formaterror(comp.sucessMessage,data));
                     }
         
                     if(comp.resetind === 1){
                      relatcom.setValue('');
                     }
               
                     if(comp.routingInd === 1 ){
                       setTimeout(() => {
                         window.location.replace(comp.routingLoc);
                      }, 2000);
               
                     }
                    
                  }
                  
                 )}
    
   
               }



        }

      
           


      });

     

    }else{
      this.errorDialogService.display_sucess(this.errorDialogService.formaterror("no action",null));


    }
   
  }

  customactioncode(data:any){
    this.cookieService.username(data['username'],GlobalConstants.rember);
    this.cookieService.usertokean(data['tokean'],GlobalConstants.rember);
    
  }

passwordValid(event) {
  this.passwordIsValid = event;
}


checked(event:MatCheckboxChange){
  //console.log(event.checked);
 // if(event.checked === true){
    this.cookieService.checkboxrember('1');
 // }else{
 //   this.cookieService.checkboxrember('0');
//  }

}


}
