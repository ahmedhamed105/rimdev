import { Component, OnInit } from '@angular/core';
import { AgRendererComponent } from 'ag-grid-angular';
import { FormGroup, Validators, FormBuilder, FormControl } from '@angular/forms';
import { UsersService } from '../services/users.service';

@Component({
  selector: 'app-usertypedropdown',
  templateUrl: './usertypedropdown.component.html',
  styleUrls: ['./usertypedropdown.component.css']
})
export class UsertypedropdownComponent implements AgRendererComponent {

  constructor(private fb:FormBuilder,private _usersservice:UsersService) { }

  public objects = [[]];
  jform :FormGroup  []=[];


  public params:any = {
    value : "none"
  };



  setvalue(form){
    var status;
    if(this.params.colDef.fieldgroup === 0){
     status = form.get(this.params.colDef.field).value;
     this.params.data[this.params.colDef.field] = status;

    }else{
       status = form.get(this.params.colDef.field).value;
       this.params.data[this.params.colDef.field][this.params.colDef.selectValue] = status;


    }
  }
  
  agInit(params:any):void {
      this.params = params;
   // console.log(this.params);


    

    if(this.params.colDef.Serv === ""){
    }else{

      this._usersservice.getbyurl(this.params.colDef.Serv,this.params.colDef.ip,this.params.colDef.port)
      .subscribe(data => {
        this.objects[this.params.colDef.formnum] = data;
  });
    }
 

    this.jform[this.params.colDef.formnum] = this.fb.group({
    });

 
if(this.params.colDef.fieldgroup === 0){

  this.jform[this.params.colDef.formnum].addControl(this.params.colDef.field,  new FormControl({value: this.params.data[this.params.colDef.field], disabled: this.params.colDef.fielddisable}, [Validators.required]));

}else{

  this.jform[this.params.colDef.formnum].addControl(this.params.colDef.field,  new FormControl({value: this.params.data[this.params.colDef.field][this.params.colDef.selectValue], disabled: this.params.colDef.fielddisable}, [Validators.required]));

}

  }


  refresh(params: any): any {
  //  this.params = params;
    // return true to tell the grid we refreshed successfully
    return true;
}

}
