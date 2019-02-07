import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';

import { RentACar } from '../model';



@Injectable()
export class RentacarService {

  private RentACarURL = 'http://localhost:8080/api/rentacar';

  constructor(private http: HttpClient) { }

  getRentACar(id: number): Observable<Object> {
    return this.http.get(`${this.RentACarURL}/${id}`);
  }

  saveRentACar(rentacar: Object): Promise<Object> {
    console.log(rentacar);
    console.log('POST');
    return this.http.post(`${this.RentACarURL}`, rentacar).toPromise();
  }

  getAll(): Observable<any> {
    return this.http.get(`${this.RentACarURL}/all`);
  }

  getAllFilijale(id: number): Observable<any> {
    return this.http.get(`${this.RentACarURL}/${id}/filijale`);
  }

  removeRentACar(id: number): Observable<Object> {
    return this.http.delete(`${this.RentACarURL}/${id}`);
  }

  getCenovnik(id: number): Observable<any> {
    return this.http.get(`${this.RentACarURL}/${id}/cenovnik`);
  }

  async updateRent(rentacar: Object): Promise<Object> {
    return this.http.put(`${this.RentACarURL}`, rentacar).toPromise();
  }

  search(param): Observable<any> {
    let params = new HttpParams();
    params = params.append('search',param.search);
    params = params.append('pickup',param.pickup);
    params = params.append('dropoff',param.dropoff);

    return this.http.get(`${this.RentACarURL}/search`, { params: params });
  }


}
