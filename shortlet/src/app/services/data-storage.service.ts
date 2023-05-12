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
          property_type
      )
      .subscribe((res) => {
        console.log(res);
        this.propertyType.next(res);
      });
  }

  sendComment(userComment: { comment: string }, id: number, email: string) {
    console.log(userComment, id, email);
    return this.http.post(
      environment.endpoint + `/apartment/comment/add/?apartment_id=${id}`,
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
}
