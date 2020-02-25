import { Component, OnInit, ViewChild } from '@angular/core';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { LocationServiceService } from '../services/location-service.service';
import { UsersService } from '../services/users.service';
import { EmailsService } from '../services/emails.service';
import { AgGridAngular } from 'ag-grid-angular';
import { TelesService } from '../services/teles.service';

@Component({
  selector: 'app-usertele',
  templateUrl: './usertele.component.html',
  styleUrls: ['./usertele.component.css']
})
export class UserteleComponent implements OnInit {

  @ViewChild('agGrid',{static: true}) agGrid: AgGridAngular;

  public users = [];
  public data_status = [];

  public show:boolean = false;

  constructor(private _TelesService:TelesService,private locationService: LocationServiceService,private fb:FormBuilder,private _usersservice:UsersService){}

  insertform :FormGroup;
  updateform :FormGroup;
  public device ;
  public page_number:number = 7 ;
  public selectuser;
  public selectemail;




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
     editable: true,
     resizable: true
   },
   {
     headerName : "status",
     field : "datastatusID.dstatus",
     sortable: true, 
     filter: true,        
     resizable: true,
     cellRenderer: this.CustomCombobox
   }
   
 
 ];

  CustomCombobox(params) {  
  //Find RowIndex   
  var rowIndex = params.rowIndex;  
  //create select element using javascript  
  var eSelect = document.createElement("select");  
  //Set attributes   
  eSelect.setAttribute('class', 'custom-select form-control');  
  eSelect.setAttribute('style', 'padding:0px');  
  eSelect.setAttribute('name', params.colDef.field);  
  eSelect.setAttribute('id', params.colDef.field + "_" + rowIndex);  
  //get the value of the select option  
  var value = params.data.CompanyID;  
  //create the default option of the select element  
  var eOption = document.createElement("option");  
  eOption.text = "Select";  
  eOption.value = "";  
  eSelect.appendChild(eOption);  
  if (params.colDef.field == "datastatusID.dstatus") {  
   
      var eOptionVal = document.createElement("option");  
      //Statical set data in grid ComboBox  
      this.data_status.forEach(element => {
        eOptionVal.text = element.dstatus;  
        eOptionVal.value = element;  
        eSelect.appendChild(eOptionVal); 
      });

  }  
  return eSelect;  
}  


  ngOnInit(){

    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });

    this._usersservice.getall()
    .subscribe(data => this.users = data);



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
  
    this.selectuser = this.users.filter(x => x.id == id)[0];
  
   console.log(this.selectuser);


   this.rowData =this._TelesService.getbyuser(this.selectuser.id);

    
  }


  savetele(){
    const selectedNodes = this.agGrid.api.getSelectedNodes();
    const selectedData = selectedNodes.map( node => node.data );
    const selectedDataStringPresentation = selectedData.map( node =>
       {
        console.log(node)
        console.log(node.telePrimary)

if(node.telePrimary == 0 || node.telePrimary == 1 ){
this.rowData= this._TelesService.insert(node);
}else{
  alert('enter correct value in primary field 0 or 1');

}
      });

  }

  deletetel(){

    const selectedNodes = this.agGrid.api.getSelectedNodes();
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

  alert("select User first");
}


}





}
