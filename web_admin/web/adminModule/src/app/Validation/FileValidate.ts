import { AbstractControl, ValidatorFn, ValidationErrors } from '@angular/forms';


export function FileValidate(filecount: number): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {

    console.log('Rahmedf  ' +filecount);
    if(filecount === 0){
        return { validfile: true };
      
    }else{
        return null; 
    }
}
}