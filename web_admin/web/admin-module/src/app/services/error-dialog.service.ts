import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { LanguagegoService } from './languagego.service';
import { GlobalConstants } from '../GlobalConstants';
import { Router } from '@angular/router';
import { formatDate } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class ErrorDialogService {

  public isDialogOpen: Boolean = false;

    constructor(public dialog: MatDialog) { }
    openDialog(data): any {
        if (this.isDialogOpen) {
            return false;
        }
        this.isDialogOpen = true;
        const dialogRef = this.dialog.open(ErrorDialogComponent, {
            width: '300px',
            data: data
        });

        dialogRef.afterClosed().subscribe(result => {
   //         console.log('The dialog was closed');
            this.isDialogOpen = false;
            let animal;
            animal = result;
        });
    }



    display_sucess(text){
      let data1 = {
        reason: text ,
        status: 400
      };
      this.openDialog(data1);

    }


    display_error(error){

 //console.log(error);


 let data1 = {
  reason: error.error ,
  status: error.code
};

 if(error.code === 400){
  //popup
this.openDialog(data1);

}else if(error.code === 410){
  //redirect
var URL= window.location.pathname 
if(URL !== '/login'){
  window.location.replace('/login');
}

}else{
// other errors
window.location.replace('/error?status='+ data1.status+'&reason='+data1.reason);
} 
    

      }



   public formaterror(error,map,field,date,user){

    if(error === undefined){
      error='error';
    }

if(map != null){
 // console.log(typeof(map))
  if(typeof(map)==='string'){
    error =error.replace('data',map);
    error =error.replace("{{","");
    error =error.replace("}}","");
  }else{
    for (let [key, value] of map.entries()) {
      error =error.replace(key,value);
      error =error.replace("{{","");
      error =error.replace("}}","");
  }

  }

}

if(field != null){
  if(field===undefined){
  field='field';
}
  error =error.replace('field',field);
  error =error.replace("{{","");

  error =error.replace("}}","");
}

if(date != null){
  error =error.replace('date',formatDate(date, GlobalConstants.formatlogin, GlobalConstants.locale));
  error =error.replace("{{","");

  error =error.replace("}}","");
}



if(user != null){
  error =error.replace('user',GlobalConstants.USERNAME);
  error =error.replace("{{","");

  error =error.replace("}}","");
}


return error;

      }    
}
