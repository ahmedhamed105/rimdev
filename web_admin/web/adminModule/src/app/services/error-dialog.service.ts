import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { LanguagegoService } from './languagego.service';
import { GlobalConstants } from '../GlobalConstants';
import { Ilangsearch } from '../objects/Ilangsearch';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class ErrorDialogService {

  public isDialogOpen: Boolean = false;

    constructor(private router: Router,public dialog: MatDialog,public _LanguagegoService:LanguagegoService) { }
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


    display_error(error){

   //   console.log(this.isDialogOpen +'  '+GlobalConstants.iserror);

      let data1 = {
        reason: error.error ,
        status: error.code
    };

      var  data : Ilangsearch = {
        code: error.error ,
        langcode: GlobalConstants.language
     };
     let regexplang = 
new RegExp('^[a-zA-Z]{1}[0-9]$');
     if(regexplang.test(error.error)){
      this._LanguagegoService.getlang(data).subscribe(data => {

        data1 = {
            reason: data.returnLang ,
            status: error.code
        };
      
       });
     }


      if(!GlobalConstants.iserror){
     this.openDialog(data1);
      }else{
        this.router.navigate(['/error'],{ queryParams: { status: data1.status,reason :data1.reason } });
      }

     
     
      
      }


      converttext(text){

        if(!GlobalConstants.iserror){

          var  data : Ilangsearch = {
            code: text ,
            langcode: GlobalConstants.language
         };
         
        return this._LanguagegoService.getlang(data);

     
      
      }
    }

}
