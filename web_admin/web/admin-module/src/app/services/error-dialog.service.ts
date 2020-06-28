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
            console.log('The dialog was closed');
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

 console.log(error);


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

      public formaterror(error,data){

        if(error === undefined){
          error='error';
        }
    
        if(data != null){
          console.log(error);
         var first = this.locations(error,"{{");
         var last = this.locations(error,"}}");
         var variables=[];
         var values=[];
      
         for (let i = 0; i < first.length; i++) {
      
          var mySubString = error.substring(
            first[i], 
            last[i]+2
        );
        variables.push(mySubString);
        var varSubString = error.substring(
          first[i] + 2, 
          last[i]
      );
      var value=data[varSubString];
 
  if(this.isDate(value)) {
    value=  formatDate(value, GlobalConstants.formatlogin, GlobalConstants.locale)
  } 


    
 
      values.push(value);
        }
    
        for (let j = 0; j < variables.length; j++) {
          error =error.replace(variables[j],values[j]);
        }  
    
       
        }
    
    
    
    return error;
    
          }   

          isDate(s) {
            if(isNaN(s) && !isNaN(Date.parse(s)))
                return true;
            else return false;
        }

     locations(string,substring){
            var a=[],i=-1;
            while((i=string.indexOf(substring,i+1)) >= 0) a.push(i);
            return a;
          }
}
