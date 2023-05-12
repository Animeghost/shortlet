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
    if (
      req.url === environment.endpoint + '/' ||
      req.url === environment.endpoint + '/reservation/'
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
