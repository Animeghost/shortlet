import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class AdminService {
  constructor(private http: HttpClient) {}
  getAllUsers() {
    return this.http.get(environment.endpoint + '/user');
  }

  getAllAdmins() {
    return this.http.get(environment.endpoint + '/admin');
  }

  makeUserAdmin(id: number, email: string) {
    return this.http.put(
      environment.endpoint + `/user/update/?user_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ admin_email: email }),
      }
    );
    // console.log(id);
  }

  revokeAdminAccess(id: number, email: string) {
    return this.http.put(
      environment.endpoint + `/user/update/role/?user_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ admin_email: email }),
      }
    );
  }

  getAllPendingRequest() {
    return this.http.get(environment.endpoint + '/homes/PENDING?');
  }

  rejectListing(id: number, email: string) {
    // console.log(id);
    return this.http.put(
      environment.endpoint + `/home/update/unverify?apartment_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ user_email: email }),
      }
    );
  }

  acceptListing(id: number, email: string) {
    return this.http.put(
      environment.endpoint + `/home/update/verify?apartment_id=${id}`,
      {},
      {
        headers: new HttpHeaders({ user_email: email }),
      }
    );
  }
}
