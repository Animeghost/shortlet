import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { ShortletComponent } from './shortlet.component';
import { BookingComponent } from './booking/booking.component';

const routes: Routes = [
  // {
  //   path: '',
  //   component: ShortletComponent,
  // },
  {
    path: ':id',
    component: ShortletComponent,
  },

  {
    path: ':id/booking',
    component: BookingComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ShortletRoutingModule {}
