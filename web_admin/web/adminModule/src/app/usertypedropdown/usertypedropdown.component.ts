import { Component, OnInit } from '@angular/core';
import { AgRendererComponent } from 'ag-grid-angular';
import { TelesService } from '../services/teles.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-usertypedropdown',
  templateUrl: './usertypedropdown.component.html',
  styleUrls: ['./usertypedropdown.component.css']
})
export class UsertypedropdownComponent implements AgRendererComponent {

  constructor(private _TelesService:TelesService,private fb:FormBuilder) { }

  public data_status = [];


  statusform :FormGroup;
  primaryform :FormGroup;


  private params:any = {
    value : "none"
  };

  setstatus(){

    var status = this.statusform.get('datastatusID').value;
    this.params.data.datastatusID = this.data_status.filter(x => x.id == status)[0];
  }
  

  setprimary(){

    var status = this.primaryform.get('primary').value;
    this.params.data.telePrimary = status;
  }
  
  agInit(params:any):void {
      this.params = params;
    //  console.dir(params);



      this._TelesService.getstatus()
      .subscribe(data => this.data_status = data);

          
    this.statusform = this.fb.group({
      datastatusID: [this.params.data.datastatusID.id ,[Validators.required]],
      });


      this.primaryform = this.fb.group({
        primary: [this.params.data.telePrimary ,[Validators.required]],
        });
  


  }


  refresh(params: any): any {
  //  this.params = params;
    // return true to tell the grid we refreshed successfully
    return true;
}

}
