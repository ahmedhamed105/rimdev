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
  tmpform :FormGroup;
  public password;


  public params:any = {
    value : "none"
  };



  setvalue(form){

    var text =form;
    if(this.params.colDef.parentgroup != null){
      text =text.get(this.params.colDef.parentgroup);
    }

    if(this.params.colDef.groupname != null){
      text =text.get(this.params.colDef.groupname);
      }
    
      text=  text.get(this.params.colDef.field).value;
   
    var status = this._EncryptionService.encypttext(text);

    this.password = status;

    if(this.params.colDef.parentgroup != null && this.params.colDef.groupname != null){
      this.params.data[this.params.colDef.parentgroup][this.params.colDef.groupname][this.params.colDef.field] = status;
    }else if(this.params.colDef.groupname != null && this.params.colDef.groupname == null){
      this.params.data[this.params.colDef.parentgroup][this.params.colDef.field] = status;

     }else if(this.params.colDef.groupname == null && this.params.colDef.groupname != null){
      this.params.data[this.params.colDef.groupname][this.params.colDef.field] = status;
    }else{
      this.params.data[this.params.colDef.field] = status;
    }

    

    
  }
  
  agInit(params:any):void {
      this.params = params;
   // console.log(this.params);


    this.jform[this.params.colDef.formnum] = this.fb.group({
    });


    var value =this.params.data;


    if(this.params.colDef.parentgroup != null){
      this.jform[this.params.coldef.formnum].addControl(this.params.colDef.parentgroup, new FormGroup({}));;
      this.tmpform = this.jform[this.params.coldef.formnum].get(this.params.colDef.parentgroup) as FormGroup;
      value =value[this.params.colDef.parentgroup];
    }

    if(this.params.colDef.groupname != null){
      this.jform[this.params.coldef.formnum].addControl(this.params.colDef.groupname, new FormGroup({}));;
      this.tmpform = this.jform[this.params.coldef.formnum].get(this.params.colDef.groupname) as FormGroup;
      value =value[this.params.colDef.groupname];
    }

    value = value[this.params.colDef.field];
  

  this.jform[this.params.colDef.formnum].addControl(this.params.colDef.field,  new FormControl({value: value, disabled: this.params.colDef.fielddisable}, [Validators.required]));



  }


  refresh(params: any): any {
  //  this.params = params;
    // return true to tell the grid we refreshed successfully
    return true;
}


}
