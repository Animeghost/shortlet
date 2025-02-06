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
      req.url === environment.endpoint + '/reservation/' ||
      req.url === environment.endpoint + '/update_user/' ||
      req.url === environment.endpoint + '/staff' ||
      req.url === environment.endpoint + '/admin' ||
      req.url === environment.endpoint + '/house_type' ||
      req.url === environment.endpoint + '/property_type'
      // req.url === environment.endpoint + '/signup'
      // req.url != environment.endpoint + '/verified_homes' ||
      // req.url != environment.endpoint + '/home/' + '*' ||
      // req.url != 'https://restcountries.com/v2/all'
    ) {
      console.log('here');
      const user = JSON.parse(localStorage.getItem('shortletUserData'));
      // const user_email = user.email;
      const accessToken = user.oauthAccessToken;
      // console.log(accessToken);
      const modifiedRequest = req.clone({
        headers: new HttpHeaders({
          // user_email: user_email,
          Authorization: accessToken,
          'Access-Control-Allow-Headers': 'Content-Type',
        }),
      });
      return next.handle(modifiedRequest);
    }
    return next.handle(req);
  }
}
