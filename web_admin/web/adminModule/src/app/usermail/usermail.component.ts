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

  public objects = [[]];
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

    var a=  res.sort((a, b) => {
        return a.comp.seqNum -b.comp.seqNum;
      });


      a.forEach((element,index) => {

        this.createItem(element.comp.name,element.comp.groupname,element.comp.crequired,element.comp.cpattern,element.comp.patterndesgin);
          
        this.components.push(element);

        console.log(element.select.webService)

if(element.comp.ctype == 'select'){

        if(element.select.arrayObject === 1){
          if(element.select.webService != null){

            this._usersservice.getbyurl(element.select.webService)
            .subscribe(data => {this.objects[index] = data;
              console.log(index);
            console.log(this.objects[index])});
           }

           }else{
            this.objects[index]=[];
             var res = element.select.selectValue.replace('[', '').replace(']', '').split(",");
             var res1 = element.select.selectDisplay.replace('[', '').replace(']', '').split(",");
             res1.forEach((element, index1) => {
               let  prearray = {
                 key : element,
                 value :res[index1]
               };
               this.objects[index].push(prearray);
             });
       
            
       
           }

          }



        this.errorDialogService.converttext(element.comp.ccode)
        .subscribe(data => {

          element.comp.ccode = data.returnLang;   
        });
   
       
});


    } );
    
    this.gridOptions = <GridOptions>{};
    this.gridOptions.frameworkComponents = { "cellRenderer" : UsertypedropdownComponent  };

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


 


  getuser(array){  
    var id = this.insertform.get('userID').get('id').value;

    if(id == null || id == "" ){

     this.errorDialogService.display_error("E100");
    }
  
    this.selectuser = array.filter(x => x.id == id)[0];
  
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
