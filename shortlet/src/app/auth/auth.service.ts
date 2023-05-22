import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {
  Auth,
  signOut,
  signInWithPopup,
  GoogleAuthProvider,
  FacebookAuthProvider,
  fetchSignInMethodsForEmail,
  signInWithEmailAndPassword,
  linkWithCredential,
} from '@angular/fire/auth';
import { NotificationService } from '../services/notifications.service';
import { User } from '../Model/user.model';
import { BehaviorSubject } from 'rxjs';
import { SignedinUser } from '../interface/shortlet';
import { UserRole } from '../Model/user-role.model';
import { environment } from 'src/environments/environment';
import { UserService } from '../services/user.service';

@Injectable({ providedIn: 'root' })
export class AuthService {
  activeUser: boolean;
  oauthAccessToken: string;
  private expirationTimer: any;
  user = new BehaviorSubject<User>(null);
  userRole = new BehaviorSubject<UserRole>(null);
  // provider = new FacebookAuthProvider();

  constructor(
    private http: HttpClient,
    private auth: Auth,
    private notif: NotificationService,
    private userS: UserService
  ) {}

  loginWithGoogle() {
    signInWithPopup(this.auth, new GoogleAuthProvider())
      .then(
        (authenticated_user: {
          user: any;
          providerId: string;
          _tokenResponse: any;
          operationType: 'link' | 'reauthenticate' | 'signIn';
        }) => {
          console.log(authenticated_user);
          const obj: {
            user: any;
            providerId: string;
            _tokenResponse: any;
            operationType: 'link' | 'reauthenticate' | 'signIn';
          } = { ...authenticated_user };
          console.log(obj);
          let { _tokenResponse } = obj;
          console.log(_tokenResponse);
          console.log(_tokenResponse.oauthAccessToken);
          this.oauthAccessToken = _tokenResponse.oauthAccessToken;

          let userToken: string;
          authenticated_user.user.getIdToken().then((token: string) => {
            const expiresIn = 3600;

            this.handleAuthentication(
              authenticated_user.user.email,
              authenticated_user.user.displayName,
              authenticated_user.user.photoURL,
              token,
              expiresIn,
              this.oauthAccessToken
            );
          });

          this.notif.successMessage('Successfully logged in');
        }
      )
      .catch((error) => {
        this.notif.errorMessage(error.message);
      });
  }

  autoLogin() {
    const userData: {
      email: string;
      displayName: string;
      photoUrl: string;
      oauthAccessToken: string;
      _token: string;
      _tokenExpirationDate: Date;
    } = JSON.parse(localStorage.getItem('shortletUserData'));

    const userRoleData: {
      role: string;
    } = JSON.parse(localStorage.getItem('ShotletUserRole'));

    if (!userData) {
      return;
    }

    const loadedUser = new User(
      userData.email,
      userData.displayName,
      userData.photoUrl,
      userData.oauthAccessToken,
      userData._token,
      new Date(userData._tokenExpirationDate)
    );

    const loadedUserRole = new UserRole(userRoleData.role);

    // console.log('im here');

    //checking if the token is valid
    if (loadedUser.token) {
      // console.log(' works here');
      this.user.next(loadedUser);
      this.userRole.next(loadedUserRole);
      const expirationDuration =
        new Date(userData._tokenExpirationDate).getTime() -
        new Date().getTime();
      // this.autoLogOut(expirationDuration);
    }
  }

  private handleAuthentication(
    email: string,
    displayName: string,
    photoURL: string,
    userToken: string,
    expiresIn: number,
    oauthAccessToken: string
  ) {
    this.http
      .post(
        environment.endpoint + '/signup',
        {
          name: displayName,
          email: email,
        },
        {
          headers: new HttpHeaders({ Authorization: oauthAccessToken }),
        }
      )
      .subscribe((res: SignedinUser) => {
        const userRole = new UserRole(res.role);
        this.userRole.next(userRole);
        localStorage.setItem('ShotletUserRole', JSON.stringify(userRole));
      });
    const expirationDate = new Date(new Date().getTime() + expiresIn * 1000);
    const user = new User(
      email,
      displayName,
      photoURL,
      oauthAccessToken,
      userToken,
      expirationDate
    );
    this.user.next(user);
    // this.autoLogOut(expiresIn * 1000);
    localStorage.setItem('shortletUserData', JSON.stringify(user));
    this.activeUserCheck();
  }

  logOut() {
    signOut(this.auth).then(() => {
      this.user.next(null);
      this.userRole.next(null);
      localStorage.removeItem('shortletUserData');
      localStorage.removeItem('ShotletUserRole');
      if (this.expirationTimer) {
        clearTimeout(this.expirationTimer);
      }
      this.expirationTimer = null;
      this.notif.successMessage('Successfully logged out');
    });
  }

  // autoLogOut(expirationDuration: number) {
  //   // console.log(expirationDuration);
  //   this.expirationTimer = setTimeout(() => {
  //     this.logOut();
  //   }, expirationDuration);
  // }

  private activeUserCheck() {
    this.userS.getUser().subscribe((res: { activeUser: boolean }) => {
      console.log(res.activeUser);
      const userstatus = res.activeUser;

      if (!userstatus) {
        this.notif.errorMessage(
          'Your account has been disabled, contact support for help'
        );
        this.logOut();
      } else {
        console.log(true);
      }
    });
  }
}
