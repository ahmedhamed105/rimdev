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

  insertform :FormGroup []=[];
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


        if(parent.child == null){


        }else{

       
        
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
          if(element.select.webService != null){

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


}

});


    });
    
    this.gridOptions = <GridOptions>{};
    this.gridOptions.frameworkComponents = { "cellRenderer" : UsertypedropdownComponent  };

    console.log(this.objects)
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



 


  getuser(array,form){  
    var id = form.get('userID').get('id').value;

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
  
  

onSubmit(form,serv){
  console.log(form.value);
  this.rowData=  this._usersservice.insertbyurl(form.value,serv);
  form.reset();
}











}
