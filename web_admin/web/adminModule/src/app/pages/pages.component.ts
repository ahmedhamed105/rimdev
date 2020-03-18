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
import { Idirectory } from '../objects/idirectory';
import { Observable,of  } from 'rxjs';
import { FileQueueObject, FileUploaderService } from '../services/file-uploader.service';
import { GlobalConstants } from '../GlobalConstants';
import * as _ from 'lodash';
import { formatDate } from '@angular/common';

declare var $: any;

@Component({
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
  public page : Idirectory;
  public type ;
  public isfile;

  constructor(private fileupload: FileUploaderService,private router:Router,private route: ActivatedRoute,public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup []=[];
  tmpform :FormGroup;
  public device ;
  gridOptions:GridOptions []=[];
  rowData: any []=[];

 columnDefs: Icolumdef[] []=[];
 public column:Icolumdef[] = [];
 
 public file =[] ;
 public dates =[] ;


  ngOnInit(){

    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    };



    this.isfile =false;
    this.file= [];
    this.dates =[] ;

    var menuid =this.route.snapshot.paramMap.get("id").toString();

    this.type =this.route.snapshot.paramMap.get("type");

    this._ComponentService.getmenu(this.type,menuid).subscribe(res =>{


    this.page=res;

    console.log(this.page)




   

    this.locationService.all_info(this.type === "P"?this.page.parent.pagesID.id:this.page.child.pagesID.id).then(res => {
      this.device =this.locationService.mydevice;
      console.log(this.device.devicetokean);
    });


   
    
    this._ComponentService.getbypage(this.type === "P"?this.page.parent.pagesID.id:this.page.child.pagesID.id).subscribe(res =>{

      
      

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


  this.gridOptions[parent.parent.id]= <GridOptions>{};
  this.gridOptions[parent.parent.id].frameworkComponents = { "cellRenderer" : UsertypedropdownComponent  };

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
    formnum:index,
    sortable: true, 
    filter: true, 
    editable: false,      
    resizable: true,
    checkboxSelection: false,
    cellRenderer: "cellRenderer"
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
  this.rowData[parent.parent.id] =  this._usersservice.getbyurl(parent.parent.firstmethod)

}

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


  Makeaction(array,form,group,comp,serv,related,relcom){  
    var id ; 
    if(group != null){
      id = form.get(group).get(comp).value; 
    }

    if(id == null || id == "" ){

     this.errorDialogService.display_error(1,"E100");
    }
  
    var selectobject = array.filter(x => x[comp] == id)[0];
//get table no action
if(related === 'table'){
  this.rowData[relcom] =this._usersservice.getbyvalue(serv,selectobject.id);
}
  

    
  }


  tableaction(serv,para,index,related,relcom){

    console.log(this.gridOptions[index])
    const selectedNodes = this.gridOptions[index].api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

     this.errorDialogService.display_error(1,"E103");

    }

    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
     console.log(node)

    this.rowData[index] = this._usersservice.insertbyurl(node,serv);
    

      });

  }


  displayupdate(serv,para,index,related,relcom){
 //console.log(this.gridOptions)
    const selectedNodes = this.gridOptions[index].api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

     this.errorDialogService.display_error(1,"E103");

    }

    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
        if(related === 'form'){
        console.log(node)
        if(this.isfile){
        this.fileupload.clearQueue();
        this.fileupload.addfilesuser(serv,node[para],node[para]);
        this.file.forEach(element => {
          this.insertform[relcom].get(element).setValidators([]);
          this.insertform[relcom].get(element).updateValueAndValidity();
        });

        }

        this.dates.forEach(element => {
          node[element]= formatDate(node[element], GlobalConstants.format, GlobalConstants.locale)
        });
       
          this.insertform[relcom].patchValue(node);
      }else if(related === 'table'){
        this.rowData[relcom] = this._usersservice.getbyvalue(serv,node[para]);
      }


      });

  }

  

onSubmit(form,serv,related,relcom){



     this._usersservice.insertbyurl(form.value,serv).subscribe(data => {

    console.log(data)

      if(this.rowData === undefined){
      }else{
        // get table number 
        if(related === 'table'){
        this.rowData[relcom]= of(data) ;
        }
      }
      if(this.isfile){
        this.fileupload.uploadAllinsert(data[data.length-1])
        this.fileupload.clearQueue();

      } 

      form.reset();

    });
    

 
 
}


resetform(form,serv,related,relcom){
  if(this.isfile){
    this.fileupload.clearQueue();

  } 
  form.reset();
}




}
