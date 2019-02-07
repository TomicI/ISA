import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private userAccount = 'http://localhost:8080/api/rezervacijarent';

  constructor(private http: HttpClient) { }

  getResCancel(id: number): Promise<any> {
    return this.http.get(`${this.userAccount}/cancel/${id}`).toPromise();
  }

  confirmCancel(res: object): Promise<any> {
    return this.http.put(`${this.userAccount}/cancel`, res).toPromise();
  }

}
