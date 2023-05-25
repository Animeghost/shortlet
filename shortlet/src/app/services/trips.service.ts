import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ReservationObj } from '../interface/shortlet';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { NotificationService } from './notifications.service';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class TripsService {
  newComment = new Subject();
  constructor(
    private http: HttpClient,
    private notif: NotificationService,
    private router: Router
  ) {}
  addReservation(
    id: number,
    email: string,
    checkInDate: Date,
    checkOutDate: Date,
    price: number,
    token: string
  ) {
    const checkin = this.dateConverterforCheckIn(checkInDate);
    const checkout = this.dateConverterforCheckOut(checkOutDate);
    // console.log(checkin, checkout);
    this.http
      .put(
        environment.endpoint + `/addReservation/?apartment_id=${id}`,
        {
          checkInDate: checkin,
          checkOutDate: checkout,
          price: price,
        },
        {
          headers: new HttpHeaders({ Authorization: token }),
        }
      )
      .subscribe((res) => {
        console.log(res);
        this.notif.successMessage('Reservation Successful');
        this.router.navigate(['/trips']);
      });
  }

  getAllReservations(): Observable<ReservationObj> {
    return this.http.get<ReservationObj>(
      environment.endpoint + '/reservation/'
    );
  }

  sendComment(
    userComment: { comment: string },
    reservation_id: number,
    apartment_id: number,
    email: string,
    token: string
  ) {
    console.log(userComment, reservation_id, apartment_id, email);
    return this.http.post(
      environment.endpoint +
        `/apartment/comment/add/?apartment_id=${apartment_id}&reservation_id=${reservation_id}`,
      userComment,
      {
        headers: new HttpHeaders({ Authorization: token }),
      }
    );
  }

  private dateConverterforCheckIn(reserveDate: Date) {
    const date = new Date(reserveDate);

    const year = date.toLocaleString('default', { year: 'numeric' });

    const month = date.toLocaleString('default', { month: '2-digit' });

    const day = date.toLocaleString('default', { day: '2-digit' });

    const formattedDate1 = year + '-' + month + '-' + day;
    return formattedDate1;
  }

  private dateConverterforCheckOut(reserveDate: Date) {
    const date = new Date(reserveDate);

    const year = date.toLocaleString('default', { year: 'numeric' });

    const month = date.toLocaleString('default', { month: '2-digit' });

    const day = date.toLocaleString('default', { day: '2-digit' });

    const formattedDate2 = year + '-' + month + '-' + day;
    return formattedDate2;
  }
}
