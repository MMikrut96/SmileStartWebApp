import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from 'rxjs/index';
import {catchError, retry} from 'rxjs/internal/operators';

export class HttpErrorInterceptor implements HttpInterceptor {

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req)
      .pipe(
        retry(1),
        catchError((error: HttpErrorResponse) => {
          let errorMessage = '';
          if (error.error instanceof ErrorEvent) {
            // client-side error
            errorMessage = `Błąd: ${error.error.message}`;
          } else {
            // server-side error
            errorMessage = `Kod Błędu: ${error.status}\nWiadomość: ${error.error.message}`;
          }
          window.alert(errorMessage);
          return throwError(errorMessage);
        })
      );
  }
}
