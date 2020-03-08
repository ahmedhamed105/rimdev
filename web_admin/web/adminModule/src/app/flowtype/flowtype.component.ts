import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { StatusServService } from '../services/status-serv.service';
import { FlowtypeServService } from '../services/flowtype-serv.service';
import { LocationServiceService } from '../services/location-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-flowtype',
  templateUrl: './flowtype.component.html',
  styleUrls: ['./flowtype.component.css']
})
export class FlowtypeComponent implements OnInit {

  public flowtypes = [];
  public status = [];
  public device ;
  public page_number;

  constructor(private route: ActivatedRoute,private locationService: LocationServiceService,private fb:FormBuilder,private _flowtypeserv:FlowtypeServService,private _status:StatusServService){
  
  
  }

  insertform :FormGroup;
  updateformflow :FormGroup;



   ngOnInit(){
    this.page_number =this.route.snapshot.paramMap.get("id");
    
    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });



    
this._flowtypeserv.getall()
.subscribe(data => this.flowtypes = data);

this._status.getallstatus()
.subscribe(data => this.status = data);

    this.insertform = this.fb.group({
      flowtype:['',[Validators.required,Validators.minLength(3)]],
      flowdescription: ['' ,[Validators.required,Validators.minLength(3)]],
      flowstatus: ['',[Validators.required]]
      });

      this.updateformflow = this.fb.group({
         id:['',[Validators.required]],
         flowtype:['',[Validators.required,Validators.minLength(3)]],
         flowdescription: ['' ,[Validators.required,Validators.minLength(3)]],
         flowstatus: ['',[Validators.required]]
         });




 
  
     
   

  }



  get flowtype(){
    return this.insertform.get('flowtype');
 
   }

   get flowdescription(){
    return this.insertform.get('flowdescription');
 
   }




onSubmit(){
//console.log(this.insertform.value);
this._flowtypeserv.insert(this.insertform.value)
.subscribe(
data => this.flowtypes = data
);

this.insertform.reset();

}

public selectflowtype;


getcur(){
  var id = this.updateformflow.get('id').value;
  console.log(id);
  this.selectflowtype = this.flowtypes.filter(x => x.id == id)[0];
  this.updateformflow.patchValue({
    flowtype : this.selectflowtype.flowtype,
    flowdescription:this.selectflowtype.flowdescription,
    flowstatus :this.selectflowtype.flowstatus});
  //console.log(this.selectflowtype);
}



onUpdate(){
  //console.log(this.insertform.value);
  this._flowtypeserv.insert(this.updateformflow.value)
  .subscribe(
  data => this.flowtypes = data
  );
  
  this.updateformflow.reset();
  
  }




}
