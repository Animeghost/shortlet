import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DataStorageService } from 'src/app/services/data-storage.service';
import { NotificationService } from 'src/app/services/notifications.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  constructor(
    private userS: UserService,
    private dataS: DataStorageService,
    private notif: NotificationService,
    private router: Router
  ) {}
  profileForm: FormGroup;
  username: string;
  phoneNumber: any;

  ngOnInit(): void {
    this.userS
      .getUser()
      .subscribe((res: { email: string; name: string; phoneNo: any }) => {
        console.log(res);
        this.username = res.name;

        this.phoneNumber = res.phoneNo;

        this.profileForm.patchValue({
          name: res.name,
          email: res.email,
          phoneNumber: res.phoneNo,
        });
      });
    this.profileForm = new FormGroup({
      name: new FormControl(null, Validators.required),
      phoneNumber: new FormControl(null, Validators.max(11)),
      email: new FormControl(null),
    });
  }

  keyPress(event: any) {
    // const pattern = /[0-9\+\-\ ]/;
    const pattern = /[0-9\ ]/;
    let inputChar = String.fromCharCode(event.charCode);
    if (event.keyCode != 8 && !pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  onSubmit() {
    let phoneNo = this.profileForm.value['phoneNumber'];
    let name = this.profileForm.value['name'];
    const updateUser = {
      name: name,
      phoneNo: phoneNo,
    };

    if (this.username === name && this.phoneNumber === phoneNo) {
      this.notif.warningMessage('No change was made');
      this.router.navigate(['/']);
    } else {
      if (name === '') {
        this.notif.warningMessage('please provide a name');
      } else if (phoneNo !== null && phoneNo !== '') {
        if (Object.values(phoneNo).join('').length <= 10) {
          this.notif.warningMessage('please provide 11 digits');
        } else {
          // console.log(this.profileForm.value);
          this.userS
            .updateUserInfo(updateUser, this.profileForm.value['email'])
            .subscribe((res) => {
              console.log(res);
              this.router.navigate(['/']);
              this.notif.successMessage('profile successfully updated');
              //add nvaigation code here and success message
            });
        }
      } else {
        // console.log(this.profileForm.value);
        this.userS
          .updateUserInfo(updateUser, this.profileForm.value['email'])
          .subscribe((res) => {
            console.log(res);
            this.router.navigate(['/']);
            this.notif.successMessage('profile successfully updated');
          });
      }
    }
  }
}
