import { Component, OnInit, ViewChild } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { EmailsService } from '../services/emails.service';
import { AgGridAngular } from 'ag-grid-angular';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { Ilangsearch } from '../objects/Ilangsearch';
import { GlobalConstants } from '../GlobalConstants';
import { LanguagegoService } from '../services/languagego.service';

@Component({
  selector: 'app-usermail',
  templateUrl: './usermail.component.html',
  styleUrls: ['./usermail.component.css']
})
export class UsermailComponent implements OnInit {
  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;

  public users = [];
  public data_status = [];

  public error_message;


  constructor(public _LanguagegoService:LanguagegoService,public errorDialogService: ErrorDialogService ,private _EmailsService:EmailsService,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup;
  updateform :FormGroup;
  public device ;
  public page_number:number = 6 ;
  public selectuser;
  public selectemail;

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

    this._usersservice.getall()
    .subscribe(data => this.users = data);

    this.gridOptions = <GridOptions>{};
    this.gridOptions.frameworkComponents = { "cellRenderer" : UsertypedropdownComponent  };

    this._EmailsService.getstatus()
    .subscribe(data => this.data_status = data);

    this.insertform = this.fb.group({
      emailuser: ['' ,[Validators.required,Validators.email]],
      emailPrimary: ['' ,[Validators.required]],
      userID: this.fb.group({
        id : ['',[Validators.required]]
         }),
         datastatusID: this.fb.group({
          id : ['',[Validators.required]]
           }),
      });



  }


  get iform() { return this.insertform.controls; }



  getuser(){  
    var id = this.insertform.get('userID').get('id').value;

    if(id == null || id == "" ){
      var  data : Ilangsearch = {
        code: "E100" ,
        langcode: GlobalConstants.language
     };
   this._LanguagegoService.getlang(data).subscribe(data => {

     this.errorDialogService.display_error(data.returnLang)
   });
    }
  
    this.selectuser = this.users.filter(x => x.id == id)[0];
  
   console.log(this.selectuser);


   this.rowData =this._EmailsService.getbyuser(this.selectuser.id);

    
  }


  savemail(){
    const selectedNodes = this.agGrid.api.getSelectedNodes();

    if(selectedNodes.length === 0 ){

      var  data : Ilangsearch = {
        code: "E103" ,
        langcode: GlobalConstants.language
     };
   this._LanguagegoService.getlang(data).subscribe(data => {

     this.errorDialogService.display_error(data.returnLang)
   });

    }

    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
    //    console.log(node)
    //    console.log(node.emailPrimary)

if(node.emailPrimary == 0 || node.emailPrimary == 1 ){
this.rowData= this._EmailsService.insert(node);
}else{
  var  data : Ilangsearch = {
    code: "E102" ,
    langcode: GlobalConstants.language
 };
this._LanguagegoService.getlang(data).subscribe(data => {

 this.errorDialogService.display_error(data.returnLang)
});

}
      });

  }

  deletemail(){

    const selectedNodes = this.agGrid.api.getSelectedNodes();

    if(selectedNodes.length === 0 ){
    
      var  data : Ilangsearch = {
        code: "E103" ,
        langcode: GlobalConstants.language
     };
   this._LanguagegoService.getlang(data).subscribe(data => {

     this.errorDialogService.display_error(data.returnLang)
   });

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

  var  data : Ilangsearch = {
    code: "E100" ,
    langcode: GlobalConstants.language
 };
this._LanguagegoService.getlang(data).subscribe(data => {

 this.errorDialogService.display_error(data.returnLang)
});
}


}













}
