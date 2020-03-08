import { Component, OnInit, ViewChild } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { AgGridAngular } from 'ag-grid-angular';
import { TelesService } from '../services/teles.service';
import { GridOptions } from 'ag-grid-community';
import { UsertypedropdownComponent } from '../usertypedropdown/usertypedropdown.component';
import { ErrorDialogService } from '../services/error-dialog.service';
import { GlobalConstants } from '../GlobalConstants';
import { CookiesService } from '../services/cookies.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-usertele',
  templateUrl: './usertele.component.html',
  styleUrls: ['./usertele.component.css']
})
export class UserteleComponent implements OnInit {

  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;

  public users = [];
  public data_status = [];
 


  constructor(private route: ActivatedRoute,private cookieService: CookiesService,public errorDialogService: ErrorDialogService,private _TelesService:TelesService,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup;
  updateform :FormGroup;
  public device ;
  public page_number;
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


  this.page_number =this.route.snapshot.paramMap.get("id");


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


  getuser(){  
    var id = this.insertform.get('userID').get('id').value;

    if(id == null || id == "" ){

        this.errorDialogService.display_error("E100")
    }

   
    this.selectuser = this.users.filter(x => x.id == id)[0];
console.log(this.selectuser);

   this.rowData =this._TelesService.getbyuser(this.selectuser.id);

    
  }


  savetele(){
    const selectedNodes = this.agGrid.api.getSelectedNodes();
    if(selectedNodes.length === 0 ){
      
 this.errorDialogService.display_error("E101")

    }
    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
        console.log(node)
     //   console.log(node.telePrimary)

if(node.telePrimary == 0 || node.telePrimary == 1 ){
this.rowData= this._TelesService.insert(node);
}else{
 
 this.errorDialogService.display_error("E102")
  
}
      });

  }

  deletetel(){

    const selectedNodes = this.agGrid.api.getSelectedNodes();
    if(selectedNodes.length === 0 ){
     this.errorDialogService.display_error("E101")
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


 this.errorDialogService.display_error("E100")
  
}


}









}
