import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NewShortletComponent } from './components/new-shortlet/new-shortlet.component';
import { RegisterShortletComponent } from './components/new-shortlet/register-shortlet/register-shortlet.component';
import { BookingComponent } from './components/shortlet/booking/booking.component';
import { ShortletComponent } from './components/shortlet/shortlet.component';

const routes: Routes = [
  // { path: '', redirectTo: '', pathMatch: 'full' },
  { path: '', component: HomeComponent },
  { path: 'shortlet/:id/booking', component: BookingComponent },
  {
    path: 'shortlet/:id',
    component: ShortletComponent,
    children: [
      // { path: '', redirectTo: 'overview', pathMatch: 'full' },
      // { path: 'shortlet/:id/booking', component: BookingComponent },
    ],
  },
  {
    path:'host/shortlets', 
    component: NewShortletComponent
  },
  {
    path: 'host/shortlets/new',
    component: RegisterShortletComponent
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
