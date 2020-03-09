import { Component, OnInit, ViewChild } from '@angular/core';
import { Validators, FormGroup, FormBuilder, FormArray, FormControl } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { EmailsService } from '../services/emails.service';
import { AgGridAngular } from 'ag-grid-angular';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { ComponentService } from '../services/component.service';
import { ActivatedRoute } from '@angular/router';
import { Icolumdef } from '../objects/Icolumdef';

@Component({
  selector: 'app-usermail',
  templateUrl: './usermail.component.html',
  styleUrls: ['./usermail.component.css']
})
export class UsermailComponent implements OnInit {
  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;

  public objects = [[]];
  public components = [];
  public arraystatic = [];
  public error_message;


  constructor(private route: ActivatedRoute,public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private _EmailsService:EmailsService,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup []=[];
  tmpform :FormGroup;
  public device ;
  public page_number;
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
    this.page_number =this.route.snapshot.paramMap.get("id");

    this.gridOptions = <GridOptions>{};
    this.gridOptions.frameworkComponents = { "cellRenderer" : UsertypedropdownComponent  };

    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });


   
    
    this._ComponentService.getbypage(this.page_number).subscribe(res =>{

 
      

      res.forEach((parent,indexp) => {

        this.insertform.push(new FormGroup({}));

        this.insertform[indexp] = this.fb.group({
        });


        this.errorDialogService.converttext(parent.parent.pcodeTittle)
        .subscribe(data => {
  
          parent.parent.pcodeTittle = data.returnLang;   
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



        this.errorDialogService.converttext(element.comp.ccode)
        .subscribe(data => {

          element.comp.ccode = data.returnLang;   
        });
   
       
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

  this.errorDialogService.converttext(element.comp.ccode)
  .subscribe(data => {    
    var col = this.gridOptions.columnApi.getColumn(this.columnDefs[index+1].field);

    // obtain the column definition from the column
    var colDef = col.getColDef();

    // update the header name
    colDef.headerName = data.returnLang;

    // the column is now updated. to reflect the header change, get the grid refresh the header
    this.gridOptions.api.refreshHeader();
    
  });

 

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

  this.errorDialogService.converttext(element.comp.ccode)
  .subscribe(data => {    
    var col = this.gridOptions.columnApi.getColumn(this.columnDefs[index+1].field);

    // obtain the column definition from the column
    var colDef = col.getColDef();

    // update the header name
    colDef.headerName = data.returnLang;

    // the column is now updated. to reflect the header change, get the grid refresh the header
    this.gridOptions.api.refreshHeader();
    
  });

 

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


  this.errorDialogService.converttext(element.comp.ccode)
  .subscribe(data => {    
//console.log(element.comp.groupname === null? element.comp.name:element.comp.groupname.toString());

    var col = this.gridOptions.columnApi.getColumn(this.columnDefs[index+1].field);

    // obtain the column definition from the column
    var colDef = col.getColDef();

    // update the header name
    colDef.headerName = data.returnLang;

    // the column is now updated. to reflect the header change, get the grid refresh the header
    this.gridOptions.api.refreshHeader();
    
  });

 

}else{

  this.errorDialogService.converttext(element.comp.ccode)
  .subscribe(data => {    
    // update the header name
    element.comp.ccode = data.returnLang;

    
  });

}





 
});


}

});


    });
    
  

    console.log(this.columnDefs)
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

     this.errorDialogService.display_error("E100");
    }
  
    var selectobject = array.filter(x => x[comp] == id)[0];

   this.rowData =this._usersservice.getbyvalue(serv,selectobject.id);

    
  }


  tableaction(serv){

    console.log(this.gridOptions)
    const selectedNodes = this.gridOptions.api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

     this.errorDialogService.display_error("E103");

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
  console.log(form.value);
  this.rowData=  this._usersservice.insertbyurl(form.value,serv);
  form.reset();
}











}
