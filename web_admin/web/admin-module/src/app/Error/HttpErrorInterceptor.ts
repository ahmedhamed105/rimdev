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
import { SpinnerService } from '../services/spinner.service';


@Injectable({
  providedIn: 'root'
})
   export class HttpErrorInterceptor implements HttpInterceptor {
      InterceptorSkipHeader = 'X-Skip-Interceptor';

    constructor(private spinnerService: SpinnerService,private router: Router,public errorDialogService: ErrorDialogService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      const startTime = Date.now();
      let status: string;
      let errorMessage = '';
      this.spinnerService.show();
      return next.handle(request)
        .pipe(
          tap(
            event => {
              status = '';
              if (event instanceof HttpResponse) {
                this.spinnerService.hide();
                status = 'succeeded';
            //    console.log(event);
              }
            },
            error => {
              this.spinnerService.hide();

              if(error.url=== 'https://jsonip.com')
              {
              
              }else{
                  if (error.error === undefined) {
                    // server-side error
                    errorMessage = error.message;
                    
                  } else {
                    
                // client-side error
                  errorMessage = error.error.message;
                  }

                  let error_ob={
                    code : error.status,
                    error :errorMessage
                  }
      
                  this.errorDialogService.display_error(error_ob);
            

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