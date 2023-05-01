import { Component } from '@angular/core';
import { AuthService } from 'src/app/auth/auth.service';

@Component({
  selector: 'app-new-shortlet',
  templateUrl: './new-shortlet.component.html',
  styleUrls: ['./new-shortlet.component.css'],
})

export class NewShortletComponent {

  constructor(private authS: AuthService) {}

  isUserAuth: any;

  ngOnInit() {
    this.authS.user.subscribe((user) => {
      this.isUserAuth = user;
    });
  }

  authenticateWithGoogle() {
    this.authS.loginWithGoogle();
  }
}
