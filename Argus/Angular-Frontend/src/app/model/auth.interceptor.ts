/*import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor{
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    // throw new Error('Method not implemented.');

    const idToken = loadStorage.getItem('id_token');

    if (idToken) {
        const cloned = req.clone({
          headers: req.headers.set('Authorization', 'Bearer ' + idToken)
        });

        return next.handle(cloned);
    }
    else{
        return next.handle(req);
    }
  }
}
*/
