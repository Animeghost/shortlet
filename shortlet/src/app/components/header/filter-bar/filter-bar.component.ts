import { Component } from '@angular/core';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { NotificationService } from 'src/app/services/notifications.service';

@Component({
  selector: 'app-filter-bar',
  templateUrl: './filter-bar.component.html',
  styleUrls: ['./filter-bar.component.css'],
})
export class FilterBarComponent {
  constructor(
    private dataS: DataStorageService,
    private notif: NotificationService
  ) {}
  toggleActiveApartment: string;

  filterbar: { name: string; image: string; value: string }[] = [
    {
      name: 'Show all',
      image: '../../../../assets/svg/overview.svg',
      value: 'ALL',
    },
    {
      name: 'Apartment',
      image: '../../../../assets/property/apatment.jpg',
      value: 'APARTMENT',
    },
    {
      name: 'Guest House',
      image: '../../../../assets/property/guest-house.jpg',
      value: 'GUESTHOUSE',
    },

    {
      name: 'Tiny Home',
      image: '../../../../assets/property/tiny-home.jpg',
      value: 'TINY_HOME',
    },
    {
      name: 'Castle',
      image: '../../../../assets/property/castles.jpg',
      value: 'CASTLE',
    },
    {
      name: 'Hotel',
      image: '../../../../assets/property/hotel.jpg',
      value: 'HOTEL',
    },
    {
      name: 'Bed & Breakfast',
      image: '../../../../assets/property/bed-breakfast.jpg',
      value: 'BED_AND_BREAKFAST',
    },
    {
      name: 'Tent',
      image: '../../../../assets/property/tent.jpg',
      value: 'TENT',
    },
    {
      name: 'Tree house',
      image: '../../../../assets/property/tree-house.jpg',
      value: 'TREEHOUSE',
    },
  ];

  getProperty(apartment: string) {
    this.toggleActiveApartment = apartment;
    // this.searchS.propertyType.next(apartment)
    if (apartment === 'ALL') {
      this.dataS.getShortlets().subscribe(
        (res) => {
          this.dataS.returnAllHomes.next(res);
        },
        (error) => this.notif.errorMessage(error.message)
      );
    } else {
      this.dataS.getSelectedApartment(apartment);
    }

    // console.log(apartment);
  }
}
