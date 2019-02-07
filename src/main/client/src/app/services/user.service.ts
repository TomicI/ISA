import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RentACar, User, NewPass, RezervacijaRent } from '../model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userAccount = 'http://localhost:8080/api/useraccount';

  constructor(private http: HttpClient) { }

  getUserRentACar(): Observable<RentACar> {
    return this.http.get<RentACar>(`${this.userAccount}/getRentACar`);
  }

  async getUser(): Promise<User> {
    return await this.http.get<User>(`${this.userAccount}/getUser`).toPromise();
  }

  async getRes(): Promise<RezervacijaRent[]> {
    return await this.http.get<RezervacijaRent[]>(`${this.userAccount}/getResVeh`).toPromise();
  }

  async getResHist(): Promise<RezervacijaRent[]> {
    return await this.http.get<RezervacijaRent[]>(`${this.userAccount}/getResVehHist`).toPromise();
  }

}
