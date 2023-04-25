import { NgModule } from '@angular/core';
import { ListingComponent } from './listing/listing.component';
import { RegisterShortletComponent } from './register-shortlet/register-shortlet.component';
import { NewShortletComponent } from './new-shortlet.component';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
    declarations:[ListingComponent, RegisterShortletComponent, NewShortletComponent],
    imports:[
        ReactiveFormsModule,
        CommonModule,
        RouterModule,
        MatTabsModule,
        MatSortModule,
        MatPaginatorModule,
        MatTableModule,
        MatInputModule,
        MatFormFieldModule,
    ],
    exports:[ListingComponent, RegisterShortletComponent, NewShortletComponent]
})
export class NewShortletModule {}