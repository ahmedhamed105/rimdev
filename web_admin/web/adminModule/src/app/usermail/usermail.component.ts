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

@Component({
  selector: 'app-usermail',
  templateUrl: './usermail.component.html',
  styleUrls: ['./usermail.component.css']
})
export class UsermailComponent implements OnInit {
  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;

  public users = [];
  public data_status = [];
  public components = [];
  public arraystatic = [];
  public error_message;


  constructor(public _ComponentService: ComponentService,public errorDialogService: ErrorDialogService ,private _EmailsService:EmailsService,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup;
  tmpform :FormGroup;
  public items: FormArray;
  public device ;
  public page_number:number = 6 ;
  public selectuser;

  gridOptions:GridOptions;




  rowData: any;
 
 
 columnDefs=[
 
   {
     headerName : "select row",
     sortable: true, 
     width : 100,
     filter: true,
     checkboxSelection: true
   },
   {
     headerName : "ID",
     field : "id",
     sortable: true, 
     filter: true
   },
   {
     headerName : "email",
     field : "emailuser",
     sortable: true, 
     filter: true,        
     editable: true,
     resizable: true
   },
   {
     headerName : "Primary",
     field : "emailPrimary",
     sortable: true, 
     filter: true,        
     editable: true,
     resizable: true,
     cellRenderer: "cellRenderer",
   },
   {
     headerName : "status",
     field : "datastatusID",
     sortable: true, 
     filter: true,  
     resizable: true,
     cellRenderer: "cellRenderer"
   }
   
 
 ];




  ngOnInit(){

    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });


    this.insertform = this.fb.group({
    });
    
    this._ComponentService.getbypage(this.page_number).subscribe(res =>{
      res.forEach(element => {

        this.errorDialogService.converttext(element.comp.ccode)
        .subscribe(data => {
          element.comp.ccode = data.returnLang;   
       //   this.items = this.insertform.get('items') as FormArray;
        //  this.items.push(this.createItem(element.comp.name));

        this.createItem(element.comp.name,element.comp.groupname,element.comp.crequired,element.comp.cpattern,element.comp.patterndesgin);
          
          this.components.push(element);
        });
   

});


    } );
    


    this._usersservice.getall()
    .subscribe(data => this.users = data);

    this.gridOptions = <GridOptions>{};
    this.gridOptions.frameworkComponents = { "cellRenderer" : UsertypedropdownComponent  };

    this._EmailsService.getstatus()
    .subscribe(data => this.data_status = data);


  }


createItem(child,group,req,pat,dpattern) {
if(group != null){
  if(this.insertform.get(group)== null){
    this.insertform.addControl(group, new FormGroup({}));
  }
  this.tmpform = this.insertform.get(group) as FormGroup;

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
    
    this.insertform.addControl(child,  new FormControl('', [Validators.required,Validators.pattern(dpattern)]));
  }else if(req === 1 && pat === 0){
    this.insertform.addControl(child,  new FormControl('', [Validators.required]));
  }else if(req === 0 && pat === 1){
    this.insertform.addControl(child,  new FormControl('', [Validators.pattern(dpattern)]));
  }else{
    this.insertform.addControl(child,  new FormControl(''));
  }

}
 
  }


  get iform() { return this.insertform.controls; }


  getArray(i: any,j: any,st : number): any[] {
   
    if(st === 1){
      return this[i];
    }
    else{
      this.arraystatic= [];
      var res = i.replace('[', '').replace(']', '').split(",");
      var res1 = j.replace('[', '').replace(']', '').split(",");
      res1.forEach((element, index) => {
        let  prearray = {
          key : element,
          value :res[index]
        };
        this.arraystatic.push(prearray);
      });

     

      return this.arraystatic;
    }

      }



  getuser(){  
    var id = this.insertform.get('userID').get('id').value;

    if(id == null || id == "" ){

     this.errorDialogService.display_error("E100");
    }
  
    this.selectuser = this.users.filter(x => x.id == id)[0];
  
   console.log(this.selectuser);


   this.rowData =this._EmailsService.getbyuser(this.selectuser.id);

    
  }


  savemail(){
    const selectedNodes = this.agGrid.api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

     this.errorDialogService.display_error("E103");

    }

    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
    //    console.log(node)
    //    console.log(node.emailPrimary)

if(node.emailPrimary == 0 || node.emailPrimary == 1 ){
this.rowData= this._EmailsService.insert(node);
}else{
 this.errorDialogService.display_error("E102");
}
      });

  }

  deletemail(){

    const selectedNodes = this.agGrid.api.getSelectedNodes();

    if(selectedNodes.length === 0 ){
    
     this.errorDialogService.display_error("E103");
    }
    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {

  
  this.rowData= this._EmailsService.delete(node);


      });
  }
  
  

onSubmit(){
//console.log(this.insertform.value);
if(this.selectuser != null){
  console.log(this.insertform.value);

  this.rowData=  this._EmailsService.insert(this.insertform.value);

  this.insertform.reset();

}else{

 this.errorDialogService.display_error("E100")

}


}











}
