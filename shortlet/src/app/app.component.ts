import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  OnInit,
} from '@angular/core';
import { AuthService } from './auth/auth.service';
import { NavigationEnd, Router } from '@angular/router';
import { filter } from 'rxjs';
import { DataStorageService } from './services/data-storage.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit, AfterViewInit {
  currentUrl: string;
  dontRender: boolean = true;
  constructor(
    private authS: AuthService,
    private router: Router,
    private dataS: DataStorageService,
    private _location: Location,
    private cdr: ChangeDetectorRef
  ) {}
  title = 'shortlet';

  ngOnInit(): void {
    this.authS.autoLogin();
  }

  ngAfterViewInit(): void {
    this.dataS.notFoundPageActive.subscribe((res) => {
      console.log(res);
      if (res == false) {
        this.dontRender = false;
        this.cdr.detectChanges();
      } else {
        this.dontRender = true;
        this.cdr.detectChanges();
      }
    });
  }
}
