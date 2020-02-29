import { Component, OnInit, ViewChild } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { AgGridAngular } from 'ag-grid-angular';
import { TelesService } from '../services/teles.service';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { LanguagegoService } from '../services/languagego.service';
import { Ilangsearch } from '../objects/Ilangsearch';
import { GlobalConstants } from '../GlobalConstants';

@Component({
  selector: 'app-usertele',
  templateUrl: './usertele.component.html',
  styleUrls: ['./usertele.component.css']
})
export class UserteleComponent implements OnInit {

  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;

  public users = [];
  public data_status = [];
 


  constructor(public _LanguagegoService:LanguagegoService,public errorDialogService: ErrorDialogService,private _TelesService:TelesService,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup;
  updateform :FormGroup;
  public device ;
  public page_number:number = 7 ;
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
     headerName : "phone No",
     field : "phoneNo",
     sortable: true, 
     filter: true,        
     editable: true,
     resizable: true
   },
   {
     headerName : "Primary",
     field : "telePrimary",
     sortable: true, 
     filter: true,        
     cellRenderer: "cellRenderer",
     resizable: true
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


    this._TelesService.getstatus()
    .subscribe(data => this.data_status = data);



    this.insertform = this.fb.group({
      phoneNo: ['' ,[Validators.required,Validators.minLength(8),Validators.maxLength(11),Validators.pattern('^\\d{8,11}$')]],
      telePrimary: ['' ,[Validators.required]],
      userID: this.fb.group({
        id : ['',[Validators.required]]
         }),
         datastatusID: this.fb.group({
          id : ['',[Validators.required]]
           }),
      });



  }



  get iform() { return this.insertform.controls; }


  frnch(){

    GlobalConstants.language= 'FR';
  }

  english(){

    GlobalConstants.language= 'EN';
  }

  arabic(){

    GlobalConstants.language= 'AR';
  }

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
  



   this.rowData =this._TelesService.getbyuser(this.selectuser.id);

    
  }


  savetele(){
    const selectedNodes = this.agGrid.api.getSelectedNodes();
    if(selectedNodes.length === 0 ){
      
  var  data : Ilangsearch = {
    code: "E101" ,  //user first
    langcode: GlobalConstants.language
 };
this._LanguagegoService.getlang(data).subscribe(data => {

 this.errorDialogService.display_error(data.returnLang)
});
  
    }
    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
        console.log(node)
     //   console.log(node.telePrimary)

if(node.telePrimary == 0 || node.telePrimary == 1 ){
this.rowData= this._TelesService.insert(node);
}else{
 
  var  data : Ilangsearch = {
    code: "E102" ,  //user first
    langcode: GlobalConstants.language
 };
this._LanguagegoService.getlang(data).subscribe(data => {

 this.errorDialogService.display_error(data.returnLang)
});
  

}
      });

  }

  deletetel(){

    const selectedNodes = this.agGrid.api.getSelectedNodes();
    if(selectedNodes.length === 0 ){
      var  data : Ilangsearch = {
        code: "E101" ,  //user first
        langcode: GlobalConstants.language
     };
    this._LanguagegoService.getlang(data).subscribe(data => {
    
     this.errorDialogService.display_error(data.returnLang)
    });
    }
    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {

  
  this.rowData= this._TelesService.delete(node);


      });
  }
  
  

onSubmit(){
//console.log(this.insertform.value);
if(this.selectuser != null){
  console.log(this.insertform.value);

  this.rowData=  this._TelesService.insert(this.insertform.value);

  this.insertform.reset();

}else{

  
  var  data : Ilangsearch = {
    code: "E100" ,  //user first
    langcode: GlobalConstants.language
 };
this._LanguagegoService.getlang(data).subscribe(data => {

 this.errorDialogService.display_error(data.returnLang)
});
  
}


}









}
