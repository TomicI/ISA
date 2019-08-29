import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {RentACar, User, NewPass, RezervacijaRent, Invite} from '../model';

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

  searchFriends(s: Object):Promise<User[]>{
    console.log("s " + s);
  return this.http.get<User[]>(`${this.userAccount}/search/`+ s).toPromise();
  }

  friendRequests(): Promise<Invite[]>{
    return this.http.get<Invite[]>(`${this.userAccount}/friendRequests`).toPromise();
  }

  async sendFriendrequest(invite: Object): Promise<Object> {
    console.log(invite);
    console.log('POST');
    return this.http.post(`${this.userAccount}/sendRequest`, invite).toPromise();
  }

  getFriends():Promise<User[]>{
    return this.http.get<User[]>(`${this.userAccount}/friends/`).toPromise();
  }

  aRequest(invite: Object): Promise<User>{
    console.log(invite);
    console.log('PUT');
    return this.http.put<User>(`${this.userAccount}/aRequest`, invite).toPromise();
  }

  eRequest(invite: Object): Promise<User>{
    console.log(invite);
    console.log('PUT');
    return this.http.put<User>(`${this.userAccount}/eRequest`, invite).toPromise();
  }

  deleteFriend(user: Object): Promise<User>{
    console.log(user);
    console.log('PUT');
    return this.http.put<User>(`${this.userAccount}/deleteFriend`, user).toPromise();
  }
}
