import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Observable, Observer, Subscription } from 'rxjs';
import { User } from 'src/app/Model/user.model';
import { AuthService } from 'src/app/auth/auth.service';
import { AdminService } from 'src/app/services/admin.service';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { NotificationService } from 'src/app/services/notifications.service';

export interface ExampleTab {
  label: string;
  content: string;
}

export interface UserData {
  id: number;
  name: string;
  email: string;
}

export interface AdminData {
  id: number;
  name: string;
  email: string;
}

export interface RequestData {
  address: string;
  country: string;
  id: number;
  name: string;
  state: string;
}

export interface RowData {
  email: string;
  id: number;
  name: string;
  phoneNo: number;
}
export interface RowDataForListingRequest {
  address: string;
  country: string;
  id: number;
  name: string;
  state: string;
}

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit, OnDestroy {
  users: any;
  admins: any;
  requests: any;
  username = '';
  user_email: string;
  hold_userdata: RowData;
  hold_rejectListing_Data: RowDataForListingRequest;
  hold_acceptListing_Data: RowDataForListingRequest;
  noPendingReq: boolean;
  noUsers: boolean;
  noAdmins: boolean;
  getAlluserSub: Subscription;
  getAllAdmins: Subscription;
  pendingReqSub: Subscription;
  userSub: Subscription;
  asyncTabs: Observable<ExampleTab[]>;

  // any property name with 1 is for the request table
  // any request with 2 or nothing e.g dataSource is for thw users table

  displayedColumns: string[] = ['name', 'email', 'action'];
  displayedColumns1: string[] = ['name', 'country', 'action'];

  dataSource: MatTableDataSource<UserData>;
  dataSource1: MatTableDataSource<RequestData>;
  dataSource2: MatTableDataSource<AdminData>;

  @ViewChild('paginator1') paginator1: MatPaginator;
  @ViewChild('sort1') sort1: MatSort;

  @ViewChild('paginator2') paginator2: MatPaginator;
  @ViewChild('sort2') sort2: MatSort;

  @ViewChild('paginator3') paginator3: MatPaginator;
  @ViewChild('sort3') sort3: MatSort;

  constructor(
    private dataS: DataStorageService,
    private adminS: AdminService,
    private authS: AuthService,
    private notif: NotificationService
  ) {}

  ngOnInit(): void {
    this.getAlluserSub = this.adminS.getAllUsers().subscribe(
      (res) => {
        this.users = res;
        if (this.users.length === 0) {
          this.noUsers = false;
        } else {
          this.noUsers = true;
        }
        this.dataSource = new MatTableDataSource(this.users);
        this.dataSource.paginator = this.paginator2;
        this.dataSource.sort = this.sort2;
        // console.log('hello');
      },
      (error) => this.notif.errorMessage(error.message)
    );

    this.getAlluserSub = this.adminS.getAllAdmins().subscribe(
      (res) => {
        this.admins = res;
        if (this.admins.length === 0) {
          this.noAdmins = false;
        } else {
          this.noAdmins = true;
        }
        this.dataSource2 = new MatTableDataSource(this.admins);
        this.dataSource2.paginator = this.paginator3;
        this.dataSource2.sort = this.sort3;
      },
      (error) => this.notif.errorMessage(error.message)
    );

    this.userSub = this.authS.user.subscribe(
      (user: User) => {
        this.user_email = user.email;
      },
      (error) => this.notif.errorMessage(error.message)
    );

    this.pendingReqSub = this.adminS.getAllPendingRequest().subscribe(
      (res) => {
        this.requests = res;

        if (this.requests.length === 0) {
          this.noPendingReq = false;
        } else {
          this.noPendingReq = true;
        }
        this.dataSource1 = new MatTableDataSource(this.requests);
        this.dataSource1.paginator = this.paginator1;
        this.dataSource1.sort = this.sort1;
      },
      (error) => this.notif.errorMessage(error.message)
    );
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  applyFilter1(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource1.filter = filterValue.trim().toLowerCase();

    if (this.dataSource1.paginator) {
      this.dataSource1.paginator.firstPage();
    }
  }

  applyFilter2(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource2.filter = filterValue.trim().toLowerCase();

    if (this.dataSource2.paginator) {
      this.dataSource2.paginator.firstPage();
    }
  }

  getDataFromAdminClick(row: RowData) {
    console.log(row);
    this.hold_userdata = row;
    this.username = row.name;
  }

  makeUserAdmin() {
    console.log(this.hold_userdata);
    this.adminS.makeUserAdmin(this.hold_userdata.id, this.user_email).subscribe(
      (res: { message: string }) => {
        console.log(res);
        this.notif.successMessage(res.message);
        setTimeout(() => {
          window.location.reload();
        }, 3000);
      },
      (error) => {
        // console.log(error);
        console.log(error.message);
        this.notif.errorMessage(error.message);
      }
    );
  }

  removeAdmin() {
    this.adminS
      .revokeAdminAccess(this.hold_userdata.id, this.user_email)
      .subscribe(
        (res: { message: string }) => {
          console.log(res);
          this.notif.successMessage(res.message);
          setTimeout(() => {
            window.location.reload();
          }, 3000);
        },
        (error) => {
          // console.log(error);
          console.log(error.message);
          this.notif.errorMessage(error.message);
        }
      );
  }

  onReject(row: RowDataForListingRequest) {
    // console.log(row);
    this.hold_rejectListing_Data = row;
  }

  rejectListing() {
    this.adminS
      .rejectListing(this.hold_rejectListing_Data.id, this.user_email)
      .subscribe(
        (res) => {
          // console.log(res);
          this.notif.successMessage('Listing successfully rejected');
          setTimeout(() => {
            window.location.reload();
          }, 3000);
        },
        (error) => {
          console.log(error.message);
          this.notif.errorMessage(error.message);
        }
      );
  }

  onAccept(row: RowDataForListingRequest) {
    // console.log(row);
    this.hold_acceptListing_Data = row;
  }

  acceptListing() {
    this.adminS
      .acceptListing(this.hold_acceptListing_Data.id, this.user_email)
      .subscribe(
        (res) => {
          console.log(res);
          this.notif.successMessage('Listing successfully accepted!');
          setTimeout(() => {
            window.location.reload();
          }, 3000);
        },
        (error) => {
          console.log(error.message);
          this.notif.errorMessage(error.message);
        }
      );
  }

  ngOnDestroy(): void {
    this.getAlluserSub.unsubscribe();
    this.userSub.unsubscribe();
    this.pendingReqSub.unsubscribe();
  }
}
