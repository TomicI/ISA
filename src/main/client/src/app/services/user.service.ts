import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RentACar } from '../model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userAccount = 'http://localhost:8080/api/useraccount';

  constructor(private http: HttpClient) { }

  getUserRentACar(): Observable<RentACar> {
    return this.http.get<RentACar>(`${this.userAccount}/getRentACar`);
  }


}
