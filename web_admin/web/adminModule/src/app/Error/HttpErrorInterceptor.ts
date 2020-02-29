import {
    HttpEvent,
    HttpInterceptor,
    HttpHandler,
    HttpRequest,
    HttpResponse,
    HttpErrorResponse
   } from '@angular/common/http';
   import { Observable, throwError } from 'rxjs';
   import { retry, catchError } from 'rxjs/operators';
import { ErrorDialogService } from '../services/error-dialog.service';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
   export class HttpErrorInterceptor implements HttpInterceptor {


    constructor(public errorDialogService: ErrorDialogService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request)
        .pipe(
          retry(1),
          catchError((error: HttpErrorResponse) => {
            let errorMessage = '';

            if (error.error instanceof ErrorEvent) {
              // client-side error
              errorMessage = `${error.error.message}`;
            } else {
              // server-side error
          
               errorMessage = `${error.error.message}`;
              

            }
            let data = {};
                data = {
                    reason: errorMessage ,
                    status: error.status
                };
          //  window.alert(errorMessage);
          this.errorDialogService.display_error(errorMessage);
            return throwError(error);
          })
        )
    }
   }