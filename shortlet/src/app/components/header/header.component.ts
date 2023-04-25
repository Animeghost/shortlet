import { Component, OnDestroy, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { Subscription, filter, map, take } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { HandlingClosingProfileTab } from 'src/app/services/handling-profile.service';
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
  // switchTextClick :boolean =false

  constructor(
    private authS: AuthService,
    private closeTab: HandlingClosingProfileTab,
    private router: Router,
    private dataS: DataStorageService,
    private notif: NotificationService
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
      (user) => {
        this.isAuthenticated = user;
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
}
