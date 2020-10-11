import { Injectable } from '@angular/core';
import { HTTP_INTERCEPTORS, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { AuthService } from "./auth.service";

// const TOKEN_HEADER_KEY = 'Authorization';

@Injectable()
export class AuthInterceptorService //implements HttpInterceptor
{

  constructor(private token: AuthService) {
  }

  /*intercept(req: HttpRequest<any>, next: HttpHandler) {
    let authReq = req;
    const token = this.token.getToken();
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + token) });
    }
    return next.handle(authReq);
  }*/

  /*intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (sessionStorage.getItem('username') && sessionStorage.getItem('token')) {
      req = req.clone({
        setHeaders: {
          Authorization: sessionStorage.getItem('token')
        }
      })
    }
    return next.handle(req);
  }*/
}
