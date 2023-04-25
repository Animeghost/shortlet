import { NgModule } from '@angular/core';
import { NotFoundComponent } from './not-found.component';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { NotFoundRoutingModule } from './not-found.routing.module';

@NgModule({
  declarations: [NotFoundComponent],
  imports: [RouterModule, CommonModule, NotFoundRoutingModule],
})
export class NotFoundModule {}
