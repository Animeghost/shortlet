import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';

import { TripsComponent } from './trips.component';
import { TripsRoutingModule } from './trips-routing.module';

@NgModule({
  declarations: [TripsComponent],
  imports: [
    RouterModule,
    CommonModule,
    TripsRoutingModule,
    MatTabsModule,
    MatSortModule,
    MatPaginatorModule,
    MatTableModule,
    MatInputModule,
    MatFormFieldModule,
  ],
  exports: [TripsComponent],
})
export class TripsModule {}
