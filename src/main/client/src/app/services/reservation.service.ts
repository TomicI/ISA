import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private userAccount = 'http://localhost:8080/api/rezervacijarent';

  private reservation = 'http://localhost:8080/api/rezervacija';

  constructor(private http: HttpClient) { }

  async saveRes(res: object):Promise<any>{
    return await this.http.post(`${this.userAccount}`, res).toPromise();
  }

  getResCancel(id: number): Promise<any> {
    return this.http.get(`${this.userAccount}/cancel/${id}`).toPromise();
  }

  confirmCancel(res: object): Promise<any> {
    return this.http.put(`${this.userAccount}/cancel`, res).toPromise();
  }

  getUserReservation():Observable<any>{
    return this.http.get(`${this.reservation}/getAllUser`);
  }

  getUserReservationHist():Observable<any>{
    return this.http.get(`${this.reservation}/getAllHistUser`);
  }

  getUserReservationId(id:number):Observable<any>{
    return this.http.get(`${this.reservation}/${id}`);
  }



}
