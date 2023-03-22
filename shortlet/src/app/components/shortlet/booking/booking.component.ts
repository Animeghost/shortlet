import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { Shortlet } from 'src/app/interface/shortlet';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { NotificationService } from 'src/app/services/notifications.service';
import { PaystackOptions } from 'angular4-paystack';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css'],
})
export class BookingComponent implements OnDestroy, OnInit {
  checkin: Date;
  checkout: Date;
  nights: number;
  calculateNumberOfNights: number;
  total: number;
  isAuth_Subcription: Subscription;
  isAuthenticated;
  emailForPayment: string;
  totalForPaystack: number;
  title: string;
  reference;

  constructor(
    private dataStorage: DataStorageService,
    private activatedRoute: ActivatedRoute,
    private authS: AuthService,
    private notif: NotificationService
  ) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((data) => {
      console.log(data);
      let id: number = data['id'];
      this.displayShortlet(id);
    });

    this.isAuth_Subcription = this.authS.user.subscribe((user) => {
      this.isAuthenticated = user;
      this.emailForPayment = user.email;

      // console.log(this.emailForPayment);
    });

    this.checkin = this.activatedRoute.snapshot.queryParams['checkin'];
    this.checkout = this.activatedRoute.snapshot.queryParams['checkout'];
    this.nights = this.activatedRoute.snapshot.queryParams['nights'];

    this.calculateBill();
    this.reference = `${Math.ceil(Math.random() * 10e10)}`;
  }

  // options: PaystackOptions = {
  //   amount: this.total,
  //   email: this.emailForPayment,
  //   ref: `${Math.ceil(Math.random() * 10e10)}`,
  // };

  shortletData: Partial<Shortlet> = {};
  shortletPictures: any = [];

  displayShortlet(id: number) {
    this.dataStorage.displayShortlet(id).subscribe(
      (response) => {
        this.shortletData = response;
        this.calculateBill();
        this.shortletPictures = response.pictures
        // this.shortletPictures = response.pictures; //pictures of shortlet from API
      },
      (error) => console.log(error)
    );
  }

  calculateBill() {
    this.calculateNumberOfNights = this.shortletData.price * this.nights;
    this.total = this.calculateNumberOfNights + 107 + 231;
    this.totalForPaystack = this.total * 100;
  }

  googleAuth() {
    this.authS.loginWithGoogle();
  }

  // onPayment() {
  //   if (this.isAuthenticated) {
  //     // console.log('authenticated');
  //     this.getEmailFromLocalStorage();
  //   } else {
  //     this.notif.warningMessage('Please login to book');
  //   }
  // }

  private getEmailFromLocalStorage() {
    const user = JSON.parse(localStorage.getItem('shortletUserData'));
    console.log(user);
    this.emailForPayment = user.email;
  }

  paymentInit() {
    console.log('Payment initialized');
  }

  paymentDone(ref: any) {
    this.title = 'Payment successfull';
    console.log(this.title, ref);
  }

  paymentCancel() {
    console.log('payment failed');
  }

  ngOnDestroy(): void {
    this.isAuth_Subcription.unsubscribe();
  }
}