import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpResponse,
    HttpErrorResponse
   } from '@angular/common/http';
   import { Observable, throwError } from 'rxjs';
   import { retry, catchError, tap, finalize } from 'rxjs/operators';
import { ErrorDialogService } from '../services/error-dialog.service';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { GlobalConstants } from '../GlobalConstants';


@Injectable({
  providedIn: 'root'
})
   export class HttpErrorInterceptor implements HttpInterceptor {
      InterceptorSkipHeader = 'X-Skip-Interceptor';

    constructor(private router: Router,public errorDialogService: ErrorDialogService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const startTime = Date.now();
      let status: string;
      let errorMessage = '';

      return next.handle(request)
        .pipe(
          tap(
            event => {
              status = '';
              if (event instanceof HttpResponse) {
                status = 'succeeded';
                GlobalConstants.iserror =false;

            //    console.log(event);
              }
            },
            error => {
              if(error.url=== 'https://jsonip.com')
              {
              
              }else{
 
                status = 'failed'; 
                //    console.log(error); 
                  
                  if (error.error === undefined) {
                    // server-side error
                    errorMessage = error.message;
                    
                  } else {
                    
                // client-side error
                  errorMessage = error.error.message;
                  }
      
                  if(error.status === 400){
                    GlobalConstants.iserror =false;
             // window.alert(errorMessage);
      
      
                  }else if(error.status === 410){
                    GlobalConstants.iserror =false;

                  }else{
                   // console.log("ahmed hamed");
                  //  console.log(error);
                    GlobalConstants.iserror =true; 
                  } 
      
                  let error_ob={
                    code : error.status,
                    error :errorMessage
                  }
      
                  this.errorDialogService.display_error(0,error_ob);
            

              }
              
             
         
          }
          ),
          finalize(() => {
            const elapsedTime = Date.now() - startTime;
            const message = request.method + " " + request.urlWithParams +" "+ status 
            + " in " + elapsedTime + "ms";
            
            this.logDetails(message);
          })
      );
    }
    private logDetails(msg: string) {
      console.log(msg);
    }

  }