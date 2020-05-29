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

  showSpinner: boolean;
  public objects = [[]];
  public components = [];
  public arraystatic = [];
  public type ;
  public isfile;

  public pagenumber = 12;

  constructor(private spinnerService: SpinnerService,private renderer:Renderer2,private elementRef: ElementRef,private _MenushareService:MenushareService,private cookieService: CookiesService,private _EncryptionService:EncryptionService,private fileupload: FileUploaderService,public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

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
 public background ;



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




    this._MenushareService.updatelang(undefined); 
    this._MenushareService.updatemenu(undefined);



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
    this.file= [];
    this.dates =[] ;


  
    GlobalConstants.pageid = this.pagenumber.toString();


    this.locationService.all_info().then(res => {
      this.device =this.locationService.mydevice;

      this.cookieService.username(this.device['username'],GlobalConstants.rember);
      this.cookieService.usertokean(this.device['usertokean'],GlobalConstants.rember);

   //   console.log(this.device['username']+" "+this.device['usertokean']);

       this._ComponentService.getbackground().subscribe(background =>{

    //    console.log(background);
        if(background.size === 0){
          this.createImage('assets/img/avatar04.png');
        }else{
          this.createImageFromBlob(background);
        }

        
     
  
    
    this._ComponentService.getbypage().subscribe(res =>{

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
    cellRenderer: ""
  };
  this.column.push(b);
 // this.columnDefs[parent.parent.id][0] = b;
  

a.forEach((element,index) => {

  var b : Icolumdef;

  var parentin=indexp*a.length;

if(element.comp.ctype === 'label'){

  if(element.comp.parentGroup != null || element.comp.parentGroup != undefined){
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

  }else{
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

  }

 
 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);

 

}else if(element.comp.ctype === 'input'){


  if(element.comp.parentGroup != null || element.comp.parentGroup != undefined){

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
  }else{
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
    
  }

  
 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);
  
 

}else if(element.comp.ctype === 'password'){

  

  if(element.comp.parentGroup != null || element.comp.parentGroup != undefined){

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

  }else{

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
    
  }

 // this.columnDefs[parent.parent.id][index+1]= b;
 this.column.push(b);
  
 

}else if(element.comp.ctype === 'select'){

  if(element.comp.parentGroup != null || element.comp.parentGroup != undefined){
    b = {
      headerName : element.comp.ccode,
      field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname,
      Serv: element.select.webService,
      selectDisplay:element.select.selectDisplay,
      selectValue:element.select.selectValue,
      fieldgroup: element.comp.groupname === undefined? 0 : 1,
      groupname : element.comp.groupname === undefined?null:element.comp.groupname,
      parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
      fielddisable: element.comp.disable === 1 ? true:false,
      disabled : element.comp.disable === 1 ? true:false,
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

  }else{

    b = {
      headerName : element.comp.ccode,
      field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname,
      Serv: element.select.webService,
      selectDisplay:element.select.selectDisplay,
      selectValue:element.select.selectValue,
      fieldgroup: element.comp.groupname === undefined? 0 : 1,
      groupname : element.comp.groupname === undefined?null:element.comp.groupname,
      parentgroup: element.comp.parentGroup=== undefined?null:element.comp.parentGroup,
      fielddisable: element.comp.disable === 1 ? true:false,
      disabled : element.comp.disable === 1 ? true:false,
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

    
  }

  //console.log(element.comp.groupname === undefined? element.comp.name:element.comp.groupname)


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
    
  });

  if(this.isfile){
    this.fileupload.clearQueue();
  }

 
  });
 



  }



   
completeItem = (item: FileQueueObject, response: any) => {
  this.onCompleteItem.emit({ item, response });
 }


 addfiles($event,name,index,pageid,parentid,compid,insert,parameter,ip,port,filecount,maxfilesize,fileCounterr,fileSizeerr,filetypes,fileTypeerror) {

  const fileBrowser = $event.target;
 
  this.fileupload.addToQueue(fileBrowser.files,name,index,pageid,parentid,compid,insert,parameter,ip,port,filecount,maxfilesize,fileCounterr,fileSizeerr,filetypes,fileTypeerror);
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
        window.location.replace(routingLoc);
       }

    },
    error=>{

      form.reset();
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
 // if(event.checked === true){
    this.cookieService.checkboxrember('1');
 // }else{
 //   this.cookieService.checkboxrember('0');
//  }

}


getUrl()
{
  return "url('https://images.pexels.com/photos/531880/pexels-photo-531880.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500')";
}


}
