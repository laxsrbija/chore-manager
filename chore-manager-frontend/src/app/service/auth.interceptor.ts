import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {AuthService} from "./auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const key = this.authService.getAuthToken();
    if (key) {
      const updatedRequest = request.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest',
          'Authorization': 'Basic ' + key
        }
      });

      return next.handle(updatedRequest);
    } else {
      const updatedRequest = request.clone({
        setHeaders: {
          'Content-Type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest'
        }
      });

      return next.handle(updatedRequest);
    }
  }
}
