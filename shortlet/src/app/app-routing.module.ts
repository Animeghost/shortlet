import { NgModule } from '@angular/core';
import {
  AuthGuard,
  redirectUnauthorizedTo,
  redirectLoggedInTo,
} from '@angular/fire/auth-guard';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { ProfileComponent } from './components/profile/profile.component';
import { NewShortletComponent } from './components/new-shortlet/new-shortlet.component';
import { RegisterShortletComponent } from './components/new-shortlet/register-shortlet/register-shortlet.component';
import { BookingComponent } from './components/shortlet/booking/booking.component';
import { ShortletComponent } from './components/shortlet/shortlet.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AdminComponent } from './components/admin/admin.component';

const redirectUnauthorizedToLogin = () => redirectUnauthorizedTo(['']);
const redirectLoggedInToHome = () => redirectLoggedInTo(['']);

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: 'home',
    loadChildren: () =>
      import('./components/home/home.module').then((m) => m.HomeModule),
  },
  {
    path: 'trips',
    loadChildren: () =>
      import('./components/trips/trips.module').then((m) => m.TripsModule),
  },

  {
    path: 'shortlet',
    loadChildren: () =>
      import('./components/shortlet/shortlet-module.module').then(
        (m) => m.ShortletModule
      ),
  },
  // { path: 'shortlet/:id/booking', component: BookingComponent },
  // {
  //   path: 'shortlet/:id',
  //   component: ShortletComponent,
  // },

  {
    path: 'profile',
    loadChildren: () =>
      import('./components/profile/profile.module').then(
        (m) => m.ProfileModule
      ),
  },

  //mogen will handle this
  {
    path: 'host/shortlets',
    component: NewShortletComponent,
  },
  {
    path: 'host/shortlets/new',
    component: RegisterShortletComponent,
  },
  //mogen will handle this

  {
    path: 'admin',
    loadChildren: () =>
      import('./components/admin/admin.module').then((m) => m.AdminModule),
  },
  {
    path: 'not-found',
    loadChildren: () =>
      import('./components/not-found/not-found.module').then(
        (m) => m.NotFoundModule
      ),
  },
  // { path: '**', redirectTo: '/not-found' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
