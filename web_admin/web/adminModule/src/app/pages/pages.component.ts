import { Component, EventEmitter, OnInit, Output, ViewChild } from '@angular/core';
import { Validators, FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { EmailsService } from '../services/emails.service';
import { AgGridAngular } from 'ag-grid-angular';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { ComponentService } from '../services/component.service';
import { ActivatedRoute, ParamMap, Router, NavigationEnd } from '@angular/router';
import { Icolumdef } from '../objects/Icolumdef';
import { Idirectory } from '../objects/idirectory';
import { Observable,of  } from 'rxjs';
import { FileQueueObject, FileUploaderService } from '../services/file-uploader.service';
import { GlobalConstants } from '../GlobalConstants';
import * as _ from 'lodash';

declare var $: any;

@Component({
  selector: 'app-pages',
  templateUrl: './pages.component.html',
  styleUrls: ['./pages.component.css']
})
export class PagesComponent implements OnInit {
  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;
  @Output() onCompleteItem = new EventEmitter();
  queue: Observable<FileQueueObject[]> []=[];


  public objects = [[]];
  public components = [];
  public arraystatic = [];
  public page : Idirectory;
  public type ;
  public isfile;

  constructor(private fileupload: FileUploaderService,private router:Router,private route: ActivatedRoute,public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private _EmailsService:EmailsService,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup []=[];
  tmpform :FormGroup;
  public device ;
  gridOptions:GridOptions;
  rowData: any;

 columnDefs: Icolumdef []=[{
  headerName : "select row",
  field : null,
  Serv: "",
  selectDisplay:"",
  selectValue:"",
  fieldgroup:0,
  groupname:"",
  formnum:0,
  sortable: true, 
  filter: true, 
  editable: false,      
  resizable: true,
  checkboxSelection: true,
  cellRenderer: ""
}];
 
 


  ngOnInit(){

    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };



    this.isfile =false;


    var menuid =this.route.snapshot.paramMap.get("id").toString();

    this.type =this.route.snapshot.paramMap.get("type");

    this._ComponentService.getmenu(this.type,menuid).subscribe(res =>{


    this.page=res;




    this.gridOptions = <GridOptions>{};
    this.gridOptions.frameworkComponents = { "cellRenderer" : UsertypedropdownComponent  };

    this.locationService.all_info(this.type === "P"?this.page.parent.pagesID.id:this.page.child.pagesID.id).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });


   
    
    this._ComponentService.getbypage(this.type === "P"?this.page.parent.pagesID.id:this.page.child.pagesID.id).subscribe(res =>{

      
      

      res.forEach((parent,indexp) => {

        this.insertform.push(new FormGroup({}));

        this.insertform[indexp] = this.fb.group({
        });



        this.components.push(parent);


        if(parent.child != null && parent.parent.parentType === 'form'){

        
        var a=  parent.child.sort((a, b) => {
          return a.comp.seqNum -b.comp.seqNum;
        });

        

      a.forEach((element,index) => {
     
        var parentin=indexp*a.length;


        if(element.comp.ctype == 'button'){


        }else{



          this.createItem(element.comp.name,element.comp.groupname,element.comp.crequired,element.comp.cpattern,element.comp.patterndesgin,indexp);
        
          if(element.comp.ctype == 'input' &&  element.input.inputtypeID.itype == 'file'){
             
             this.isfile = true;

           this.queue[index] = this.fileupload.queue(index);
           this.fileupload.onCompleteItem = this.completeItem;

          }
        
        
        }
        


if(element.comp.ctype == 'select'){

        if(element.select.arrayObject === 1){
          if(element.select.webService != undefined){

            this._usersservice.getbyurl(element.select.webService)
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



  var a=  parent.child.sort((a, b) => {
    return a.comp.seqNum -b.comp.seqNum;
  });

  

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
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: ""
  };
  this.columnDefs[index+1]= b;


 

}else if(element.comp.ctype === 'input'){

  var b : Icolumdef= {
    headerName : element.comp.ccode,
    field : element.comp.groupname === undefined? element.comp.name:element.comp.groupname+'.'+element.comp.name,
    Serv: "",
    selectDisplay:"",
    selectValue:"",
    fieldgroup:0,
    groupname:"",
    formnum:0,
    sortable: true, 
    filter: true, 
    editable: true,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer : ""
  };
  this.columnDefs[index+1]= b;

  
 

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
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: true,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: "cellRenderer"
  };
  this.columnDefs[index+1]= b;



}else{

}





 
});


}

if(parent.parent.firstmethod === undefined){

}else{
  console.log("go");
  this.rowData =  this._usersservice.getbyurl(parent.parent.firstmethod)

}

});


    });
    
  });

   // console.log(this.columnDefs)





  }



   
completeItem = (item: FileQueueObject, response: any) => {
  this.onCompleteItem.emit({ item, response });
 }


 addfiles($event,name,index,pageid,parentid,compid,insert,parameter) {

  const fileBrowser = $event.target;
  

  if (fileBrowser.files[0].size > GlobalConstants.max_size) {
    alert('size is alrger than ');
     return false;
 }

 if (!GlobalConstants.allowed_types.includes(fileBrowser.files[0].type)) {
   alert('Only Images are allowed ( JPG | PNG )');
     return false;
 }

 
  this.fileupload.addToQueue(fileBrowser.files,name,index,pageid,parentid,compid,insert,parameter);
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


  Makeaction(array,form,group,comp,serv){  
    var id ; 
    if(group != null){
      id = form.get(group).get(comp).value; 
    }

    if(id == null || id == "" ){

     this.errorDialogService.display_error(1,"E100");
    }
  
    var selectobject = array.filter(x => x[comp] == id)[0];

   this.rowData =this._usersservice.getbyvalue(serv,selectobject.id);

    
  }


  tableaction(serv){

    console.log(this.gridOptions)
    const selectedNodes = this.gridOptions.api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

     this.errorDialogService.display_error(1,"E103");

    }

    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
     console.log(node)
    //    console.log(node.emailPrimary)

this.rowData= this._usersservice.insertbyurl(node,serv);

      });

  }

  

onSubmit(form,serv){



     this._usersservice.insertbyurl(form.value,serv).subscribe(data => {

    console.log(data)

      if(this.rowData === undefined){
      }else{
        this.rowData= of(data) ;
      }
      if(this.isfile){
        this.fileupload.uploadAllinsert(data)
        this.fileupload.clearQueue();

      } 

      form.reset();

    });
    

 
 
}




}
