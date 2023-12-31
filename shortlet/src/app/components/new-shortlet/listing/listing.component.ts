import { ListRange } from '@angular/cdk/collections';
import { Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { AuthService } from 'src/app/auth/auth.service';
import { Listings } from 'src/app/interface/shortlet';
import { User } from 'src/app/Model/user.model';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { UserData } from '../../admin/admin.component';
import { NotificationService } from 'src/app/services/notifications.service';
import { NewShortletService } from 'src/app/services/new-shortlet.service';
import { AdminService } from 'src/app/services/admin.service';

@Component({
  selector: 'app-listing',
  templateUrl: './listing.component.html',
  styleUrls: ['./listing.component.css'],
})
export class ListingComponent {
  user_email: string;
  username: string;
  newListing: any;
  noPendingListings: boolean = false;
  token: string;
  pendingListingsArray: any = [];

  displayedColumns: string[] = [
    'name',
    'address',
    'country',
    'maxNoOfGuests',
    'status',
    'action',
  ];
  dataSource: MatTableDataSource<UserData>;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private dataStorage: DataStorageService,
    private authS: AuthService,
    private notification: NotificationService,
    private newshortletS: NewShortletService,
    private adminS: AdminService
  ) {}

  ngOnInit() {
    // console.log('ng')
    //google user info displays
    this.authS.user.subscribe((user: User) => {
      if (user) {
        this.token = user.oauthAccessToken;
        this.username = user.displayName;
        this.user_email = user.email;
      }
    });

    this.newshortletS.getListing(this.user_email, this.token).subscribe(
      (response) => {
        console.log(response);
        this.newListing = response;
        this.pendingListingsArray = response;

        if (this.pendingListingsArray.length > 0) {
          this.noPendingListings = true;
        }
        this.dataSource = new MatTableDataSource(this.newListing);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;

        // console.log((this.newListing = response));

        // function to return all users, show error if usernot registered to be implemented
      },
      (error) => this.notification.errorMessage(error.message)
    );
  }

  userListings() {}

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  onSeemore(row: any) {
    // console.log(row);
    this.newshortletS.getListingData.next(row);
  }
}
