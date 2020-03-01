import { Injectable } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { ErrorDialogComponent } from '../error-dialog/error-dialog.component';
import { LanguagegoService } from './languagego.service';
import { GlobalConstants } from '../GlobalConstants';
import { Ilangsearch } from '../objects/Ilangsearch';


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


    display_error(error){

        var  data : Ilangsearch = {
            code: error ,
            langcode: GlobalConstants.language
         };
         
       this._LanguagegoService.getlang(data).subscribe(data => {
 
        data = {
            reason: data.returnLang ,
            status: 403
        };
      //  window.alert(errorMessage);
      this.openDialog(data);
       });

     
      
      }


      converttext(text){

        var  data : Ilangsearch = {
            code: text ,
            langcode: GlobalConstants.language
         };
         
        return this._LanguagegoService.getlang(data);
      
      }

}
