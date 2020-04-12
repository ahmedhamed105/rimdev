import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { LanguagegoService } from './languagego.service';
import { GlobalConstants } from '../GlobalConstants';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ErrorDialogService {

  public isDialogOpen: Boolean = false;

    constructor(public dialog: MatDialog,public _LanguagegoService:LanguagegoService) { }
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


    display_error(type,error){

 console.log(error);

     if(type === 1){
      this._LanguagegoService.getlang(error,GlobalConstants.Devicetokean,GlobalConstants.pageid).subscribe(data => {

       let data1 = {
            reason: data['message'] === undefined?error.error:data['message'] ,
            status: error.code
        };

        if(!GlobalConstants.iserror){
          this.openDialog(data1);
           }else{
            window.location.replace('/error?status='+ data1.status+'&reason ='+data1.reason);           }
      
       });
     }else{

  var URL= window.location.pathname 
if(error.code === 410 &&  URL !== '/login'){
  window.location.replace('/login');
}else{
  let data1 = {
    reason: error.error ,
    status: error.code
};
  if(!GlobalConstants.iserror){
    this.openDialog(data1);
     }else{
      window.location.replace('/error?status='+ data1.status+'&reason ='+data1.reason);
     }
 }

}
    

     
      
      }


}
