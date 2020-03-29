import { Component, OnInit } from '@angular/core';
import { AgRendererComponent } from 'ag-grid-angular';
import { UsersService } from '../services/users.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import * as CryptoJS from 'crypto-js';
import { EncryptionService } from '../services/encryption.service';

@Component({
  selector: 'app-passwordtable',
  templateUrl: './passwordtable.component.html',
  styleUrls: ['./passwordtable.component.css']
})
export class PasswordtableComponent implements AgRendererComponent{

  constructor(private _EncryptionService:EncryptionService,private fb:FormBuilder,private _usersservice:UsersService) { }

  public objects = [[]];
  jform :FormGroup  []=[];


  public params:any = {
    value : "none"
  };



  setvalue(form){
    var text=  form.get(this.params.colDef.field).value;
   
    var status = this._EncryptionService.encypttext(text);

    if(this.params.colDef.fieldgroup === 0){
     this.params.data[this.params.colDef.field] = status;

    }else{  
       this.params.data[this.params.colDef.field][this.params.colDef.selectValue] = status;


    }
  }
  
  agInit(params:any):void {
      this.params = params;
   // console.log(this.params);


    

 

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
