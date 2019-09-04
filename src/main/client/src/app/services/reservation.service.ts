import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private rentACarRes = 'http://localhost:8080/api/rezervacijarent';

  private reservation = 'http://localhost:8080/api/rezervacija';

  constructor(private http: HttpClient) { }

  async saveRes(res: object):Promise<any>{
    return await this.http.post(`${this.rentACarRes}`, res).toPromise();
  }

  getResCancel(id: number): Promise<any> {
    return this.http.get(`${this.rentACarRes}/cancel/${id}`).toPromise();
  }

  confirmCancel(res: object): Promise<any> {
    return this.http.put(`${this.rentACarRes}/cancel`, res).toPromise();
  }

  getAllAdmin(param):Observable<any>{

    let params = new HttpParams();
    params = params.append('res',param);

    return this.http.get(`${this.rentACarRes}/resAdmin`,{params:params});
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
