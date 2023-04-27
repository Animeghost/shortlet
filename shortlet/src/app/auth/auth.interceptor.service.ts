import {
  HttpEvent,
  HttpEventType,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';

export class AuthServiceInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
    if (
      req.url === '/api/' ||
      req.url === 'http://localhost:8080/reservation/'
    ) {
      console.log('here');
      const user = JSON.parse(localStorage.getItem('shortletUserData'));
      const user_email = user.email;
      const modifiedRequest = req.clone({
        headers: new HttpHeaders({ user_email: user_email }),
      });
      return next.handle(modifiedRequest);
    }

    return next.handle(req);
  }
}
