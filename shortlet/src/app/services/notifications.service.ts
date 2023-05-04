import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({ providedIn: 'root' })
export class NotificationService {
  notifications: any;
  constructor(private toastr: ToastrService) {}

  successMessage(message: string) {
    this.toastr.success(message);
  }

  errorMessage(message: string) {
    this.toastr.error(message);
  }

  warningMessage(message: string) {
    this.toastr.warning(message);
  }
}
