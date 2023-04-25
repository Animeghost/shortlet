import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Angular4PaystackModule } from 'angular4-paystack';
import { BookingComponent } from './booking/booking.component';
import { ShortletComponent } from './shortlet.component';
import { RouterModule } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';
import { ShortletRoutingModule } from './shortlet-routing.module';
import * as fromenvironment from '../../../environments/environment';

@NgModule({
  declarations: [ShortletComponent, BookingComponent],
  imports: [
    CommonModule,
    RouterModule,
    FormsModule,
    ShortletRoutingModule,
    Angular4PaystackModule.forRoot(fromenvironment.environment.paystack_key),
    MatDatepickerModule,
    MatNativeDateModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
  ],
})
export class ShortletModule {}
