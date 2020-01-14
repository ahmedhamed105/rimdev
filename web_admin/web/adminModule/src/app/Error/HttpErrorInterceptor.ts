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
   
   export class HttpErrorInterceptor implements HttpInterceptor {
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
      return next.handle(request)
        .pipe(
          retry(1),
          catchError((error: HttpErrorResponse) => {
            let errorMessage = '';
            if (error.error instanceof ErrorEvent) {
              // client-side error
              errorMessage = `Error: ${error.error.message}`;
            } else {
              // server-side error
              if(error.status == 409){
                errorMessage = `your record exist before`;
              }else if(error.status == 400){
                errorMessage = `error while insert or update record`;
              }else{
               errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
              }

            }
            window.alert(errorMessage);
            return throwError(errorMessage);
          })
        )
    }
   }