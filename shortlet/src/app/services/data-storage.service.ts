import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import {
  Listings,
  NewShortlet,
  ReservationObj,
  Shortlet,
} from '../interface/shortlet';
import { NotificationService } from './notifications.service';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class DataStorageService {
  // baseURL: string = "http://localhost:8080/";

  propertyType = new BehaviorSubject(null);
  returnAllHomes = new BehaviorSubject(null);
  notFoundPageActive = new Subject();
  pendindRequestValue = new Subject();
  checkInDateforDB: any;
  checkOutDateforDB: any;

  constructor(
    private http: HttpClient,
    private router: Router,
    private notif: NotificationService
  ) {}

  getShortlets() {
<<<<<<< HEAD
    return this.http.get<Shortlet>(environment.url + 'verified_homes');
  }

  displayShortlet(id: number): Observable<Shortlet> {
    return this.http.get<Shortlet>(environment.url + `home/?house_id=${id}`);
  }

  registerNewShortlet(formData, email) {
    // const email = '';

    const options = {
      headers: {
        user_email: email,
      },
    };

    console.log(formData);

    return this.http.post<NewShortlet>(
      environment.url + `addHome/`,
      formData,
      options
    );
  }

  getListing(email) {
    const options = {
      headers: {
        user_email: email,
      },
    };

    return this.http.get<Listings>(environment.url + `user/listings/`, options);
  }

  getUser() {
    return this.http.get(environment.url);
  }

  updateUserInfo(
    userDetails: { name: string; phoneNo: number },
    email: string
  ) {
    return this.http.put(environment.url + 'update_user/', userDetails, {
      headers: new HttpHeaders({ user_email: email }),
    });
    // console.log(userDetails);
  }

  getSelectedApartment(property_type: string) {
    this.http
      .get(
        environment.url +
          'verified_homes/search/?property_type=' +
=======
    return this.http.get<Shortlet>(environment.endpoint + '/verified_homes');
  }

  displayShortlet(id: number): Observable<Shortlet> {
    return this.http.get<Shortlet>(
      environment.endpoint + `/home/?house_id=${id}`
    );
  }

  getSelectedApartment(property_type: string) {
    this.http
      .get(
        environment.endpoint +
          '/verified_homes/search/?property_type=' +
>>>>>>> 3eb61ba60d39269f02e54c6f1dbdcef1bcd224ee
          property_type
      )
      .subscribe((res) => {
        console.log(res);
        this.propertyType.next(res);
      });
  }

<<<<<<< HEAD
  addReservation(
    id: number,
    email: string,
    checkInDate: Date,
    checkOutDate: Date,
    price: number
  ) {
    const checkin = this.dateConverterforCheckIn(checkInDate);
    const checkout = this.dateConverterforCheckOut(checkOutDate);
    // console.log(checkin, checkout);
    this.http
      .put(
        environment.url +
          `addReservation/?user_email=${email}&apartment_id=${id}`,
        {
          checkInDate: checkin,
          checkOutDate: checkout,
          price: price,
        }
      )
      .subscribe((res) => {
        console.log(res);
        this.notif.successMessage('Reservation Successful');
        this.router.navigate(['/trips']);
      });
  }

  getAllReservations(): Observable<ReservationObj> {
    return this.http.get<ReservationObj>(environment.url + 'reservation/');
  }

  getAllUsers() {
    return this.http.get(environment.url + 'user');
  }

  getAllAdmins() {
    return this.http.get(environment.url + 'admin');
  }

  makeUserAdmin(id: number, email: string) {
    return this.http.put(
      environment.url + `user/update/?user_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ admin_email: email }),
      }
    );
    // console.log(id);
  }

  revokeAdminAccess(id: number, email: string) {
    return this.http.put(
      environment.url + `user/update/role/?user_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ admin_email: email }),
      }
    );
  }

  getAllPendingRequest() {
    return this.http.get(environment.url + 'homes/PENDING?');
  }

  rejectListing(id: number, email: string) {
    // console.log(id);
    return this.http.put(
      environment.url + `home/update/unverify?apartment_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ user_email: email }),
      }
    );
  }

  acceptListing(id: number, email: string) {
    return this.http.put(
      environment.url + `home/update/verify?apartment_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ user_email: email }),
      }
    );
  }

  sendComment(userComment: { comment: string }, id: number, email: string) {
    console.log(userComment, id, email);
    return this.http.post(
      environment.url + `apartment/comment/add/?apartment_id=${id}`,
=======
  sendComment(userComment: { comment: string }, id: number, email: string) {
    console.log(userComment, id, email);
    return this.http.post(
      environment.endpoint + `/apartment/comment/add/?apartment_id=${id}`,
>>>>>>> 3eb61ba60d39269f02e54c6f1dbdcef1bcd224ee
      userComment,
      {
        headers: new HttpHeaders({ user_email: email }),
      }
    );
  }

  //get all listings under a user

  // userListings(): Observable<Listings> {
  //   return this.http.get<Listings>(
  //     `http://localhost:8080/home/?house_id=${id}`
  //   );
  // }

  //countries
<<<<<<< HEAD
  getCountry() {
    return this.http.get<any[]>('https://restcountries.com/v2/all');
  }

  getAllHouseTypes() {
    return this.http.get<any[]>('http://localhost:8080/house_type');
  }

  getAllPropertyTypes() {
    return this.http.get<any[]>('http://localhost:8080/property_type');
  }
=======
>>>>>>> 3eb61ba60d39269f02e54c6f1dbdcef1bcd224ee
}
