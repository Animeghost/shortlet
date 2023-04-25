import { Component, OnDestroy, OnInit } from '@angular/core';
import { DataStorageService } from 'src/app/services/data-storage.service';

@Component({
  selector: 'app-not-found',
  templateUrl: './not-found.component.html',
  styleUrls: ['./not-found.component.css'],
})
export class NotFoundComponent implements OnInit, OnDestroy {
  constructor(private dataS: DataStorageService) {}

  ngOnInit(): void {
    this.dataS.notFoundPageActive.next(false);
  }

  ngOnDestroy(): void {
    // console.log('i de commot');
    this.dataS.notFoundPageActive.next(true);
  }
}
