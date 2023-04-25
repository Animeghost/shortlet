import { Component, OnInit } from '@angular/core';
import { Shortlet } from 'src/app/Model/shortlet.model';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { NotificationService } from 'src/app/services/notifications.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  availableShortlets: any = [];

  constructor(
    private dataStorage: DataStorageService,
    private notif: NotificationService
  ) {}
  ngOnInit(): void {
    this.dataStorage.getShortlets().subscribe(
      (shortlet) => {
        this.availableShortlets = shortlet;
        console.log(this.availableShortlets);
      },
      (error) => this.notif.errorMessage(error.message)
    );

    this.dataStorage.propertyType.subscribe(
      (selectedProperty) => {
        if (selectedProperty) {
          this.availableShortlets = selectedProperty;
        }
      },
      (error) => this.notif.errorMessage(error.message)
    );

    this.dataStorage.returnAllHomes.subscribe(
      (all_shortlets) => {
        this.availableShortlets = all_shortlets;
      },
      (error) => this.notif.errorMessage(error.message)
    );
  }

  print(string, id) {
    console.log(string + id);
  }
}
