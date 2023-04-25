import { NgModule } from '@angular/core';
import { HomeComponent } from './home.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HomeRoutingModule } from './home-routing.module';
import { FilterBarComponent } from '../header/filter-bar/filter-bar.component';

@NgModule({
  declarations: [HomeComponent, FilterBarComponent],
  imports: [CommonModule, RouterModule, HomeRoutingModule],
  exports: [FilterBarComponent],
})
export class HomeModule {}
