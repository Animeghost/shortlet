import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Shortlet } from 'src/app/interface/shortlet';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { FormGroup, FormControl } from '@angular/forms';
import { AuthService } from 'src/app/auth/auth.service';
import { User } from 'src/app/Model/user.model';
import { user } from '@angular/fire/auth';
import { NotificationService } from 'src/app/services/notifications.service';

@Component({
  selector: 'app-shortlet',
  templateUrl: './shortlet.component.html',
  styleUrls: ['./shortlet.component.css'],
})
export class ShortletComponent implements OnInit {
  username: string;
  user_email: string;
  apartmentID: number;
  checkinDate: Date;
  checkoutDate: Date;

  numberOfNights: number;
  shortletData: Partial<Shortlet> = {};
  shortletPictures: any = [];
  shortletPrice: any;
  showAmenities: boolean = false;
  maxNoOfGuests: number;

  today: Date;

  calculateNumberOfNights: number;
  total: number;
  amenities: any = [];
  comments: any = [];
  counter: number = 1;
  serviceFee: number;
  cleaningFee: number;

  minDate = new Date();
  trial = new Date();
  twodayAhead = this.trial.setDate(this.trial.getDate() + 2);
  minDateForCheckOut = new Date(this.twodayAhead);

  buttonDisable: boolean;

  myHolidayDates = [];
  mynewArray = [];
  overallArray = [];

  UserComment: string = '';

  showShortlet: boolean;

  constructor(
    private dataStorage: DataStorageService,
    private activatedRoute: ActivatedRoute,
    private authS: AuthService,
    private notif: NotificationService
  ) {}

  ngOnInit() {
    this.activatedRoute.params.subscribe((data) => {
      let id: number = data['id'];
      this.apartmentID = data['id'];
      this.displayShortlet(id);
    });
    this.checkinDate = new Date();

    let newCheckoutDate = (this.checkoutDate = new Date());
    newCheckoutDate.setDate(new Date().getDate() + 2); // 2 days to the default checkin and out date
    this.fetchDateSelected();

    this.authS.user.subscribe((user: User) => {
      if (user) {
        this.username = user.displayName;
        this.user_email = user.email;
        // console.log(this.username);
      } else {
        this.username = null;
        this.user_email = null;
      }
    });
  }

  //to diplay hortlet
  displayShortlet(id: number) {
    this.showShortlet = false;
    this.dataStorage.displayShortlet(id).subscribe(
      (response) => {
        // console.log((this.shortletData = response));
        this.shortletData = response;
        this.overallArray = response.reservations;
        this.maxNoOfGuests = response.maxNoOfGuests;
        this.comments = response.comments;
        this.amenities = response.amenities;
        this.serviceFee = response.serviceFee;
        this.cleaningFee = response.cleaningFee;
        console.log(this.comments);

        // console.log(this.overallArray);
        for (let reserve of this.overallArray) {
          this.checkdateInbetween(reserve.checkInDate, reserve.checkOutDate);
        }

        this.calculateBill(); //details of shortlet from API
        // console.log(this.shortletPrice = response.price)
        this.shortletPictures = response.pictures; //pictures of shortlet from API
        this.disableReserveDate();
        this.showShortlet = true;
      },
      (error) => console.log(error)
    );
  }

  toggleAmenities() {
    this.showAmenities != this.showAmenities;
  }

  trialM(e) {
    this.fetchDateSelected();
    this.disableReserveDate();
  }

  fetchDateSelected() {
    // console.log('enter');
    let timeDiff = Math.abs(
      this.minDateForCheckOut.getTime() - this.checkinDate.getTime()
    );
    this.numberOfNights = Math.ceil(timeDiff / (1000 * 3600 * 24));
    console.log(this.numberOfNights);
    this.calculateBill();
  }

  calculateBill() {
    this.calculateNumberOfNights =
      this.shortletData.price * this.numberOfNights;
    this.total =
      this.calculateNumberOfNights + this.serviceFee + this.cleaningFee;
  }

  mindate() {
    this.today = new Date();
  }

  dateConverter(dateOBJ: Date) {
    const date = new Date(dateOBJ);
    const year = date.toLocaleString('default', { year: 'numeric' });
    const month = date.toLocaleString('default', { month: '2-digit' });
    const day = date.toLocaleString('default', { day: '2-digit' });
    const formattedDate = month + '/' + day + '/' + year;
    return formattedDate;
    // console.log(formattedDate); // Prints: 2022-05-04
  }

  checkdateInbetween(checkInDate, checkOutDate) {
    // console.log(new Date('2023-03-20'));
    // let testCheck1 = new Date('2023-03-20');
    // let testCheck2 = new Date('2023-03-22');

    let testCheck1 = new Date(checkInDate);
    let testCheck2 = new Date(checkOutDate);

    const date = new Date(testCheck1.getTime());
    const dates = [];
    while (date <= testCheck2) {
      dates.push(new Date(date).toLocaleDateString());
      date.setDate(date.getDate() + 1);
    }
    console.log(dates);
    for (let date1 of dates) {
      this.myHolidayDates.push(new Date(date1));
      this.mynewArray.push(new Date(date1));
    }
  }

  disableReserveDate() {
    //this will disable reserve date if both the current day and two days ahead are already reserved
    const checkIn = this.dateConverter(this.checkinDate);
    const checkOut = this.dateConverter(this.minDateForCheckOut);
    // console.log(checkIn);
    // console.log(checkOut);
    // console.log(this.myHolidayDates);
    // const testArray: Array<Date> = [...this.myHolidayDates];

    for (let date of this.mynewArray) {
      const newDate = this.dateConverter(date);
      console.log(newDate);
      if (
        new Date(newDate).getTime() === new Date(checkIn).getTime() ||
        new Date(newDate).getTime() === new Date(checkOut).getTime()
      ) {
        console.log(true);
        this.buttonDisable = true;
      } else {
        console.log(false);
        this.buttonDisable = false;
      }
    }
  }

  myHolidayFilter = (d: Date): boolean => {
    const time = d.getTime();
    return !this.myHolidayDates.find((x) => x.getTime() == time);
  };

  increment() {
    if (this.counter < this.maxNoOfGuests) {
      this.counter = this.counter + 1;
    }
    return false;
  }

  decrement() {
    if (this.counter > 1) {
      this.counter = this.counter - 1;
    }
  }

  onSendComment() {
    if (this.UserComment === '') {
      return;
    } else {
      const userComment = {
        comment: this.UserComment,
      };

      // console.log(userComment);
      this.dataStorage
        .sendComment(userComment, +this.apartmentID, this.user_email)
        .subscribe((res) => {
          console.log(res);
          this.UserComment = '';
          this.notif.successMessage('Comment added!');
          setTimeout(() => {
            window.location.reload();
          }, 2500);
        });
    }
  }
}
