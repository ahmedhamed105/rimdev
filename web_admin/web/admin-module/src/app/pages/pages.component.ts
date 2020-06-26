import { Component, EventEmitter, OnInit, Output, Renderer2, ElementRef, ChangeDetectionStrategy } from '@angular/core';
import { Validators, FormGroup, FormBuilder, FormControl } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { ComponentService } from '../services/component.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Icolumdef } from '../objects/Icolumdef';
import { Observable,of, empty  } from 'rxjs';
import { FileQueueObject, FileUploaderService } from '../services/file-uploader.service';
import { GlobalConstants } from '../GlobalConstants';
import { formatDate } from '@angular/common';
import { PasswordtableComponent } from '../passwordtable/passwordtable.component';
import { EncryptionService } from '../services/encryption.service';
import { MenulistService } from '../services/menulist.service';
import { CookiesService } from '../services/cookies.service';
import { LanguagegoService } from '../services/languagego.service';
import { MenushareService } from '../share_data/menushare.service';
import { SpinnerService } from '../services/spinner.service';


@Component({
  changeDetection: ChangeDetectionStrategy.OnPush,
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css']
})
export class PagesComponent implements OnInit {
  @Output() onCompleteItem = new EventEmitter();
  queue: Observable<FileQueueObject[]> []=[];


  public objects = [[]];
  public components = [];
  public arraystatic = [];
  public placeholders = [];
  public visables = [];
  public disables = [];
  public allcompoent = new Map<any, any>();
  public page ;
  public pagenumber ;
  public type ;
  public isfile;
  private domLayout;

  constructor(private errordis :ErrorDialogService,private spinnerService: SpinnerService,private renderer:Renderer2,private elementRef: ElementRef,private _MenushareService:MenushareService,private _LanguagegoService :LanguagegoService,private cookieService: CookiesService,private _MenulistService : MenulistService,private _EncryptionService:EncryptionService,private fileupload: FileUploaderService,private router:Router,private route: ActivatedRoute,public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

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
 passwordIsValid = false;



 

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

    var menuid =   this.locationService.getQueryParams('menuid',window.location.href); ;//this.route.snapshot.paramMap.get("id").toString();
    
    this.pagenumber =   this.locationService.getQueryParams('pageid',window.location.href); ;//this.route.snapshot.paramMap.get("id").toString();
    GlobalConstants.pageid = this.pagenumber;
    this.type = this.locationService.getQueryParams('type',window.location.href) ;  //this.route.snapshot.paramMap.get("type");

    if(menuid === undefined || this.type === undefined ){
      
      this.cookieService.username('',GlobalConstants.rember);
      this.cookieService.usertokean('',GlobalConstants.rember);
       window.location.replace("/login");

    }


    this.locationService.all_info().then(res => {

      this.device =this.locationService.mydevice;

      this.cookieService.username(this.device['username'],GlobalConstants.rember);
      this.cookieService.usertokean(this.device['usertokean'],GlobalConstants.rember);

      this._MenushareService.updateuser(this.device.userid);
      this._MenushareService.updateapp(this.device.app);
      this._MenushareService.updatenotif(this.device.notif);


    this._ComponentService.getmenu(this.type,menuid).subscribe(res =>{

    this.page=res;

   // //console.log(this.page)

      
    this._LanguagegoService.getalllang().subscribe(data => {
     // this.langs=data;
     this._MenushareService.updatelang(data);     
         });
     
         this._MenulistService.getmenu()
     .subscribe(data => {
     //  this.menus =data;
       this._MenushareService.updatemenu(data);
       });

  
    this._ComponentService.getbypage().subscribe(res =>{

     //console.log(res);
      

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
        this.visables[parentin]= element.comp.visible === 1 ? 'visible' : 'hidden';
        this.disables[parentin]= element.comp.disable=== 1 ? true : false;

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
this.tpasswords=[];
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
    
  };
  this.column.push(b);
 // this.columnDefs[parent.parent.id][0] = b;
  

a.forEach((element,index) => {

  var parentin=element.comp.id;
  this.placeholders[parentin]= element.comp.ccode;
  this.visables[parentin]= element.comp.visible === 1 ? 'visible' : 'hidden';
  this.disables[parentin]= element.comp.disable=== 1 ? true : false;

  var b : Icolumdef;


  if(element.comp.fieldEncry === 1){

    this.tpasswords.push(element);

  }



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
      
    };

    
  }

  ////console.log(element.comp.groupname === undefined? element.comp.name:element.comp.groupname)


 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);


}else{

}





 
});
this.passwords[parent.parent.id]=this.tpasswords;
this.tpasswords=[];
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

 //console.log(name,index,pageid,parentid,compid,insertserv,parameter,insertip,insertport,deleteserv,deleteip,deleteport,filecount,maxfilesize,fileCounterr,fileSizeerr,filetypes,fileTypeerror)
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


  Makeaction(array,form,group,comp,serv,related,relcom,ip,port){  
    var id ; 
    if(group != null){
      id = form.get(group).get(comp).value; 
    }

    if(id == null || id == "" ){

     this.errorDialogService.display_error("E100");
    }
  
    var selectobject = array.filter(x => x[comp] == id)[0];
//get table no action
if(related === 'table'){
  //this.rowData[relcom] =this._usersservice.getbyvalue(serv,selectobject.id,ip,port);
}
  

    
  }


  tableaction(event,rel,form,parentid,type){

   // //console.log(this.gridOptions[index])
    const selectedNodes = this.gridOptions[parentid].api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

     this.errorDialogService.display_error("E103");

    }

    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
  
        this.ret_handle(event,rel,2,node,parentid,type);
      });

  }


  displayupdate(event,rel,form,parentid,type){
 ////console.log(this.gridOptions)
    const selectedNodes = this.gridOptions[parentid].api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

     this.errorDialogService.display_error("E103");

    }

    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
        this.ret_handle(event,rel,3,node,parentid,type);
      
      });

  }

passwordValid(event) {
  this.passwordIsValid = event;
}


language(code){
  this.cookieService.language(code);
    }


    doaction(event,rel,form,parentid,type){
this.ret_handle(event,rel,1,form,parentid,type);

    }

ret_handle(event,rel,formnode,form,parentid,type){

        if( rel != undefined || rel.length > 0 ){
          rel.forEach((comp,indexp) => {
         var relvalue;
            if(type === 'button'){
              if( this.passwords[parentid].length > 0){
    
                this.passwords[parentid].forEach(element => {
                  var value;
                  var nodetmp;
                if(formnode === 1){
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
                }else if(formnode === 2){
                  if(element.comp.parentGroup != null && element.comp.groupname != null){
                    nodetmp = form[element.comp.parentGroup][element.comp.groupname][element.comp.name];
                    value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                    form[element.comp.parentGroup][element.comp.groupname][element.comp.name]=value;
                  }else if(element.comp.parentGroup != null && element.comp.groupname == null){
                    nodetmp = form[element.comp.parentGroup][element.comp.name];
                    value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                    form[element.comp.parentGroup][element.comp.name]=value;
                   }else if(element.comp.parentGroup  == null && element.comp.groupname != null){
                    nodetmp = form[element.comp.groupname][element.comp.name];
                    value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                    form[element.comp.groupname][element.comp.name]=value;
                  }else{
                    nodetmp = form[element.comp.name];
                    value = this._EncryptionService.encypttext(nodetmp.toString().trim())
                    form[element.comp.name]=value;
                  }

                }
                 
              
                });
              
              }
              if(formnode === 1){
                relvalue = form.value;
              }else if(formnode === 2){
                relvalue = form;
              }else if(formnode === 3){
                relvalue = form
              }
    
           
    
            if(relvalue != undefined){
              // alert(comp.relatedComponent +' '+ comp.service +' '+ comp.relatedParent +' '+ comp.parServ );
              
                 if(comp.relatedComponent != null && comp.service != null && comp.service != undefined){
                   this._usersservice.insertbyurl(relvalue,comp.service,comp.comIP,comp.comport)
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

                   this.controltag(2,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,comp.resetParent,comp.routingInd,comp.routingLoc);   


                              
                 });
                     }
                     
                 

                     if(comp.relatedParent != undefined && comp.parServ != undefined){

                      //console.log("dispaly");
                      
                      this._usersservice.insertbyurl(relvalue,comp.parServ,comp.comIPpar,comp.comportpar).subscribe(
                        data => {

                          if(formnode === 3){

                            //console.log("dispaly 1");
                            if(this.isfile){
                              this.fileupload.clearQueue();
                              this.fileupload.addfilesuser(data);
                              //console.log("dispaly 2");
                              this.file[comp.relatedParent].forEach(element => {
                      
                                if(element.comp.parentGroup != null && element.comp.groupname != null){
                                  this.insertform[comp.relatedParent].get(element.comp.parentGroup).get(element.comp.groupname).get(element.comp.name).setValidators([]);
                                  this.insertform[comp.relatedParent].get(element.comp.parentGroup).get(element.comp.groupname).get(element.comp.name).updateValueAndValidity();
                                }else if(element.comp.parentGroup != null && element.comp.groupname == null){
                                  this.insertform[comp.relatedParent].get(element.comp.parentGroup).get(element.comp.name).setValidators([]);
                                  this.insertform[comp.relatedParent].get(element.comp.parentGroup).get(element.comp.name).updateValueAndValidity();
                                 }else if(element.comp.parentGroup  == null && element.comp.groupname != null){
                                  this.insertform[comp.relatedParent].get(element.comp.groupname).get(element.comp.name).setValidators([]);
                                  this.insertform[comp.relatedParent].get(element.comp.groupname).get(element.comp.name).updateValueAndValidity();
                                }else{ 
                                  this.insertform[comp.relatedParent].get(element.comp.name).setValidators([]);
                                  this.insertform[comp.relatedParent].get(element.comp.name).updateValueAndValidity();
                                }
                                
                        
                              });
                      
                              }
                      
                              this.dates[comp.relatedParent].forEach(element => {
                                var valueq;
                                var nodetmp;
                              
                                if(element.comp.parentGroup != null && element.comp.groupname != null){
                                  nodetmp = relvalue[element.comp.parentGroup][element.comp.groupname][element.comp.name];
                                  valueq = formatDate(nodetmp, GlobalConstants.format, GlobalConstants.locale);
                                  relvalue[element.comp.parentGroup][element.comp.groupname][element.comp.name]=valueq;
                                }else if(element.comp.parentGroup != null && element.comp.groupname == null){
                                  nodetmp = relvalue[element.comp.parentGroup][element.comp.name];
                                  valueq = formatDate(nodetmp, GlobalConstants.format, GlobalConstants.locale);
                                  relvalue[element.comp.parentGroup][element.comp.name]=valueq;
                                 }else if(element.comp.parentGroup  == null && element.comp.groupname != null){
                                  nodetmp = relvalue[element.comp.groupname][element.comp.name];
                                  valueq = formatDate(nodetmp, GlobalConstants.format, GlobalConstants.locale);
                                  relvalue[element.comp.groupname][element.comp.name]=valueq;
                                }else{
                                  nodetmp = relvalue[element.comp.name];
                                  valueq = formatDate(nodetmp, GlobalConstants.format, GlobalConstants.locale);
                                  relvalue[element.comp.name]=valueq;
                                }
                              //  node[element]= formatDate(node[element], GlobalConstants.format, GlobalConstants.locale)
                              });
                             
                                this.insertform[comp.relatedParent].patchValue(relvalue);
                          }else if(formnode === 2){
                            if(this.isfile){
                              this.fileupload.uploadAllinsert(relvalue)
                              this.fileupload.clearQueue();
                            } 
                            this.rowData[comp.relatedParent] = of(data);

                          }else if(formnode === 1){
                            if(this.isfile){
                              this.fileupload.uploadAllinsert(relvalue)
                              this.fileupload.clearQueue();
                            } 
                            this.rowData[comp.relatedParent] = of(data);

                          }
                          //console.log("dispaly 3");
                          this.customactioncode(data);
                          
                        
                          this.controltag(2,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,comp.resetParent,comp.routingInd,comp.routingLoc);   

                        });
                       
                      }

     
                      if(comp.relatedComponent == undefined && comp.parServ == undefined && comp.relatedParent == undefined && comp.parServ == undefined && comp.resetind === 1 && comp.resetParent != undefined){
                     
                        this.controltag(2,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,comp.resetParent,comp.routingInd,comp.routingLoc);   
 

                      }
     
     
               }else{

                this.controltag(2,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,comp.resetParent,comp.routingInd,comp.routingLoc);   
 
     
                 }
    
        
    
            
            }else{
              relvalue= event.target.value;
              
              if(relvalue.length > 0){
        
            
                   if(comp.relatedComponent != undefined && comp.service != undefined){

                    var relatcom=form.get(this.allcompoent.get(comp.relatedComponent)[0]);
                    
                     this._usersservice.insertbyurl(relvalue,comp.service,comp.comIP,comp.comport)
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


                     this.controltag(1,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,undefined,comp.routingInd,comp.routingLoc);   

                   });
                       }

                       if(comp.relatedParent != undefined && comp.parServ != undefined){

                        this._usersservice.insertbyurl(relvalue,comp.parServ,comp.comIPpar,comp.comportpar).subscribe(
                          data => {
                           
                            this.rowData[comp.relatedParent] = of(data);

                            this.controltag(1,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,undefined,comp.routingInd,comp.routingLoc);   
                          });
                         
                        }
       
                        if(comp.relatedComponent == undefined && comp.parServ == undefined && comp.relatedParent == undefined && comp.parServ == undefined && comp.resetind === 1 && comp.resetParent != undefined){
                          this.controltag(1,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,comp.resetParent,comp.routingInd,comp.routingLoc);   

                        } 
       
                 }else{
                   if(comp.relatedComponent != undefined && comp.emptyServ != undefined){
                     this._usersservice.getbyurl(comp.emptyServ,comp.comIP,comp.comport)
                     .subscribe(data => {
                       
                      this.objects[comp.relatedComponent] = data;

                      this.controltag(1,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,undefined,comp.routingInd,comp.routingLoc);   


                     });
                   }
       
                     if(comp.relatedParent != undefined && comp.parServ != undefined){
                     this._usersservice.getbyurl(comp.relparentServempty,comp.comIPpar,comp.comportpar).subscribe(
                      data => {
                       
                        this.rowData[comp.relatedParent] = of(data);

                        this.controltag(1,form,comp.relatedComponent,comp.enableComp,comp.disableComp,comp.visibleComp,comp.hideComp,comp.alertAfter,comp.sucessMessage,comp.resetind,undefined,comp.routingInd,comp.routingLoc);   
                        
                      })
                    
                    }
        
       
                   }
    
    
    
            }
    
          
               

    
          });

         
    
        }else{
          this.errorDialogService.display_sucess(this.errorDialogService.formaterror("no action",null,null,null,null));


        }
       
      }
    
    
      customactioncode(data:any){

        
      }


      controltag(type,form,relatedComponent,enableComp,disableComp,visibleComp,hideComp,alertAfter,sucessMessage,resetind,resetParent,routingInd,routingLoc){
  



        //console.log(type,enableComp,disableComp,visibleComp,hideComp,alertAfter,sucessMessage,resetind,routingInd,routingLoc);
        var relatcom;

        if(relatedComponent != null){
          relatcom=form.get(this.allcompoent.get(relatedComponent)[0]);
        }

        if(disableComp != null){
          this.disables[disableComp] = true;                      
         }

         if(enableComp != null){
          this.disables[enableComp] = false;                      
         }


         if(hideComp != null){
           this.visables[hideComp] = 'hidden';                      
          }


         if(visibleComp != null){   
          this.visables[visibleComp] = 'visible';                      
         }

         if(alertAfter === 1){
      
           this.errorDialogService.display_sucess(this.errorDialogService.formaterror(sucessMessage,null,null,null,'done'));
          }

          if(resetind === 1){
            if(type === 1){
              relatcom.setValue('');
            }else if(type === 2){
              this.insertform[resetParent].reset();
              if(this.isfile){
               this.fileupload.clearQueue();
              } 
            }
           
          }
    
          if(routingInd === 1 ){
            setTimeout(() => {
              window.location.replace(routingLoc);
           }, 2000);
    
          }

      }

    signout(){
      this.cookieService.username('',GlobalConstants.rember);
      this.cookieService.usertokean('',GlobalConstants.rember);
       window.location.replace("/login");
        }
    

}




