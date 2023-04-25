import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthService } from 'src/app/auth/auth.service';
import { Shortlet } from 'src/app/interface/shortlet';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { NotificationService } from 'src/app/services/notifications.service';
import { Location } from '@angular/common';

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
  serviceFee: number;
  cleaningFee: number;
  calculateNumberOfNights: number;
  total: number;
  isAuth_Subcription: Subscription;
  isAuthenticated;
  emailForPayment: string;
  totalForPaystack: number;
  title: string;
  reference;
  guests: number;
  id: number;

  constructor(
    private dataStorage: DataStorageService,
    private activatedRoute: ActivatedRoute,
    private authS: AuthService,
    private notif: NotificationService,
    private _location: Location
  ) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((data) => {
      console.log(data);
      this.id = data['id'];
      let id: number = data['id'];
      this.displayShortlet(id);
    });

    this.isAuth_Subcription = this.authS.user.subscribe((user) => {
      this.isAuthenticated = user;
      this.emailForPayment = user.email;
    });

    this.checkin = this.activatedRoute.snapshot.queryParams['checkin'];
    this.checkout = this.activatedRoute.snapshot.queryParams['checkout'];
    this.nights = this.activatedRoute.snapshot.queryParams['nights'];
    this.guests = this.activatedRoute.snapshot.queryParams['guests'];
    this.serviceFee = +this.activatedRoute.snapshot.queryParams['service'];
    this.cleaningFee = +this.activatedRoute.snapshot.queryParams['cleaning'];
    this.calculateBill();
    this.reference = `${Math.ceil(Math.random() * 10e10)}`;
  }

  shortletData: Partial<Shortlet> = {};
  shortletPictures: any = [];

  displayShortlet(id: number) {
    this.dataStorage.displayShortlet(id).subscribe(
      (response) => {
        this.shortletData = response;
        console.log(this.shortletData);
        this.calculateBill();
        this.shortletPictures = response.pictures;
      },
      (error) => console.log(error)
    );
  }

  calculateBill() {
    this.calculateNumberOfNights = this.shortletData.price * this.nights;

    this.total =
      this.calculateNumberOfNights + this.cleaningFee + this.serviceFee;

    this.totalForPaystack = this.total * 100;
  }

  googleAuth() {
    this.authS.loginWithGoogle();
  }

  paymentInit() {
    console.log('Payment initialized');
  }

  paymentDone(ref: any) {
    this.title = 'Payment successfull';
    console.log(this.title, ref);
    if (ref.status === 'success') {
      console.log('payment was successful');
      this.dataStorage.addReservation(
        this.id,
        this.emailForPayment,
        this.checkin,
        this.checkout,
        this.total
      );
    } else {
      console.log('payment was not successfull');
    }
  }

  paymentCancel() {
    console.log('payment failed');
  }

  back() {
    this._location.back();
  }

  ngOnDestroy(): void {
    this.isAuth_Subcription.unsubscribe();
  }
}
