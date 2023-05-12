import {
  HttpEvent,
  HttpEventType,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

export class AuthServiceInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler) {
<<<<<<< HEAD
    if (req.url === '/api/' || req.url === '/api/reservation/') {
=======
    if (
      req.url === environment.endpoint + '/' ||
      req.url === environment.endpoint + '/reservation/'
    ) {
>>>>>>> 3eb61ba60d39269f02e54c6f1dbdcef1bcd224ee
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
