import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription, filter, map, take } from 'rxjs';
import { User } from 'src/app/Model/user.model';
import { AuthService } from 'src/app/auth/auth.service';
import { AdminService } from 'src/app/services/admin.service';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { HandlingClosingProfileTab } from 'src/app/services/handling-profile.service';
import { NewShortletService } from 'src/app/services/new-shortlet.service';
import { NotificationService } from 'src/app/services/notifications.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit, OnDestroy {
  isAuthenticated: any;
  isAuth_Subcription: Subscription;
  userRole_subcription: Subscription;
  closeProfileDivSubscription: Subscription;
  isProfileClicked: boolean = false;
  isUserAdmin: any;
  isUseronAdminPage: boolean;
  currentUrl: string;
  // notifications: string[] = [];
  // switchTextClick :boolean =false
  pendingReq: any = [];
  countries: any[] = [];
  pendingRequestvalue: number;
  request: boolean = false;
  token: string;

  constructor(
    private authS: AuthService,
    private closeTab: HandlingClosingProfileTab,
    private router: Router,
    private dataStorage: DataStorageService,
    private notif: NotificationService,
    private adminS: AdminService,
    private newshortletS: NewShortletService
  ) {}

  ngOnInit(): void {
    this.router.events
      .pipe(
        filter(
          (event): event is NavigationEnd => event instanceof NavigationEnd
        )
      )
      .subscribe(
        (event: NavigationEnd) => {
          // console.log(event.url);
          this.currentUrl = event.url;
          if (event.url === '/admin') {
            this.isUseronAdminPage = true;
          } else {
            this.isUseronAdminPage = false;
          }
        },
        (error) => this.notif.errorMessage(error.message)
      );

    this.isAuth_Subcription = this.authS.user.subscribe(
      (user: User) => {
        if (user) {
          this.token = user.oauthAccessToken;
          this.isAuthenticated = user;
        } else {
          this.isAuthenticated = null;
        }
      },
      (error) => this.notif.errorMessage(error.message)
    );

    this.authS.userRole
      .pipe(
        map((res) => {
          if (res) {
            if (res.role === 'ADMIN') {
              return true;
            } else {
              return false;
            }
          } else {
            return false;
          }
        })
      )
      .subscribe(
        (res) => {
          console.log(res);
          this.isUserAdmin = res;
        },
        (error) => this.notif.errorMessage(error.message)
      );

    this.onlineChecker();

    this.dataStorage.pendindRequestValue.subscribe((value) => {
      this.pendingRequestvalue = +value;
      if (this.pendingRequestvalue > 0) {
        this.request = true;
      }
    });

    this.newshortletS.getCountry().subscribe((response) => {
      this.countries = response.map((country) => {
        return { name: country.name, code: country.alpha2Code };
      });
      // console.log(this.countries);
    });
  }

  toggleProfileDiv() {
    this.isProfileClicked = !this.isProfileClicked;
  }

  OnClickOutside() {
    this.isProfileClicked = false;
  }

  authenticateWithGoogle() {
    this.authS.loginWithGoogle();
  }

  ngOnDestroy(): void {
    this.isAuth_Subcription.unsubscribe();
    this.userRole_subcription.unsubscribe();
  }

  onLogOut() {
    this.authS.logOut();
  }

  onSwitchRole() {
    if (this.currentUrl === '/admin') {
      this.router.navigate(['/']);
    } else {
      this.router.navigate(['/admin']);
    }
  }

  // private onlineChecker() {
  //   if (this.token) {
  //     this.adminS.getAllPendingRequest(this.token).subscribe((response) => {
  //       // console.log(response);
  //       this.pendingReq = response;
  //       this.pendingRequestvalue = this.pendingReq.length;
  //       this.dataStorage.pendindRequestValue.next(this.pendingRequestvalue);
  //       if (this.pendingRequestvalue > 0) {
  //         this.request = true;
  //       }
  //     });
  //   }
  // }

  private onlineChecker() {
    this.authS.user.subscribe((user) => {
      if (user) {
        this.adminS.getAllPendingRequest(this.token).subscribe((response) => {
          // console.log(response);
          this.pendingReq = response;
          this.pendingRequestvalue = this.pendingReq.length;
          this.dataStorage.pendindRequestValue.next(this.pendingRequestvalue);
          if (this.pendingRequestvalue > 0) {
            this.request = true;
          }
        });
      }
    });
  }
}
