import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {RentACar, User, NewPass, RezervacijaRent, Invite, Rezervacija} from '../model';

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

  inviteRequests(): Promise<Invite[]>{
    return this.http.get<Invite[]>(`${this.userAccount}/inviteRequests`).toPromise();
  }

  async sendFriendrequest(invite: Object): Promise<Object> {
    console.log(invite);
    console.log('POST');
    return this.http.post(`${this.userAccount}/sendRequest`, invite).toPromise();
  }

  inviteFriend(invite: Invite): Promise<Invite> {
    console.log(invite);
    console.log('POST');
    return this.http.post<Invite>(`${this.userAccount}/inviteFriend`, invite).toPromise();
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

  checkReset():Observable<any>{
    return this.http.get<any>(`${this.userAccount}/checkReset`);
  }

  aRequestI(id: number): Promise<String>{
    console.log('Delete');
    return this.http.delete<String>(`${this.userAccount}/aRequestI/`+id).toPromise();
  }

  eRequestI(invite: Object): Promise<String>{
    console.log(invite);
    console.log('PUT');
    return this.http.put<String>(`${this.userAccount}/eRequestI`, invite).toPromise();
  }

  sendMail(reservation: Rezervacija): Promise<String>{
    return this.http.put<String>(`${this.userAccount}/sendMail`, reservation).toPromise();
  }
}
