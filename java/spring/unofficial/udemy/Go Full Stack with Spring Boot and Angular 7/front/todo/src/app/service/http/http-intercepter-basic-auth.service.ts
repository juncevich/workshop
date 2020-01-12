import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { BasicAuthenticationService } from '../basic-authentication.service';

@Injectable({
  providedIn: 'root'
})
export class HttpIntercepterBasicAuthService implements HttpInterceptor {

  constructor(
    private basivAuthenticationService: BasicAuthenticationService
  ) { }

  intercept(request: HttpRequest<any>, next: HttpHandler) {

    const basicAuthHeaderString = this.basivAuthenticationService.getAuthenticatedToken();
    const username = this.basivAuthenticationService.getAuthenticatedUser();

    if (basicAuthHeaderString && username) {
      request = request.clone(
        {
          setHeaders: {
            Authorization: basicAuthHeaderString
          }
        }

      );
    }

    return next.handle(request);
  }
}
