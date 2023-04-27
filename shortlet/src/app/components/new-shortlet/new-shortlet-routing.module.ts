import { NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import {
    AuthGuard,
    redirectUnauthorizedTo,
    redirectLoggedInTo,
  } from '@angular/fire/auth-guard';
import { NewShortletComponent } from './new-shortlet.component';
import { RegisterShortletComponent } from './register-shortlet/register-shortlet.component';

// import { ShortletComponent } from './shortlet.component';
// import { BookingComponent } from './booking/booking.component';

const redirectUnauthorizedToLogin = () => redirectUnauthorizedTo(['']);

const routes: Routes = [
  // {
  //   path: '',
  //   component: ShortletComponent,
  // },
  {
    path: '',
    component: NewShortletComponent,
  },
  {
    path: 'host/shortlets/new',
    component: RegisterShortletComponent,
    canActivate: [AuthGuard],
    data: { authGuardPipe: redirectUnauthorizedToLogin },
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class NewShortletRoutingModule {}
