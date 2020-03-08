import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { CurrencyServService } from '../services/currency-serv.service';
import { StatusServService } from '../services/status-serv.service';
import { LocationServiceService } from '../services/location-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-currency',
  templateUrl: './currency.component.html',
  styleUrls: ['./currency.component.css']
})
export class CurrencyComponent implements OnInit {

  public currency = [];
  public status = [];

  constructor(private route: ActivatedRoute,private locationService: LocationServiceService,private fb:FormBuilder,private _currencyserv:CurrencyServService,private _status:StatusServService){}

  insertform :FormGroup;
  updateform :FormGroup;
  public device ;
  public page_number;
  public selectcurrency;

  ngOnInit(){

    this.page_number =this.route.snapshot.paramMap.get("id");

    this.locationService.all_info(this.page_number).then(res => {
      this.device =this.locationService.status;
      console.log(this.device.tokean);
    });

this._currencyserv.getall()
.subscribe(data => this.currency = data);

this._status.getallstatus()
.subscribe(data => this.status = data);

    this.insertform = this.fb.group({
      currencyname:['',[Validators.required,Validators.minLength(3)]],
      currencyISO: ['' ,[Validators.required,Validators.minLength(3)]],
      currencystatus: ['',[Validators.required]]
      });

      this.updateform = this.fb.group({
         id:['',[Validators.required]],
         currencyname:['',[Validators.required,Validators.minLength(3)]],
         currencyISO: ['' ,[Validators.required,Validators.minLength(3)]],
         currencystatus: ['',[Validators.required]]
         });

  }

  get currencyname(){
    return this.insertform.get('currencyname');
 
   }

   get currencyISO(){
    return this.insertform.get('currencyISO');
 
   }

loadApiData(){


}




onSubmit(){
//console.log(this.insertform.value);
this._currencyserv.insert(this.insertform.value)
.subscribe(
data => this.currency = data
);

this.insertform.reset();

}




getcur(){
  var id = this.updateform.get('id').value;
  console.log(id);
  this.selectcurrency = this.currency.filter(x => x.id == id)[0];
  this.updateform.patchValue({
    currencyname : this.selectcurrency.currencyname,
    currencyISO:this.selectcurrency.currencyISO,
    currencystatus :this.selectcurrency.currencystatus});
  //console.log(this.selectcurrency);
}



onUpdate(){
  //console.log(this.insertform.value);
  this._currencyserv.insert(this.updateform.value)
  .subscribe(
  data => this.currency = data
  );
  
  this.updateform.reset();
  
  
  }


}