import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JwtResponse } from './response';
import { UserLogin, User, NewPass } from '../model';



const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8080/api/auth/signin';
  private signupUrl = 'http://localhost:8080/api/auth/signup';

  private passWordUrl = 'http://localhost:8080/api/auth/changePass';
  private adminPassWordUrl = 'http://localhost:8080/api/auth/changeAdminPass';
  private nameUrl = 'http://localhost:8080/api/auth/changeName';
  private emailUrl = 'http://localhost:8080/api/auth/changeEmail';
  private usernameUrl = 'http://localhost:8080/api/auth/changeUsername';

  private redirectUrl = 'http://localhost:8080/api/auth/test';

  

  constructor(private http: HttpClient) {
  }

  attemptAuth(credentials: UserLogin): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.loginUrl, credentials, httpOptions);
  }

  signUp(user: User): Observable<JwtResponse> {
    return this.http.post<JwtResponse>(this.signupUrl, user, httpOptions);
  }

  changePass(newPass: NewPass): Promise<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.passWordUrl}`, newPass).toPromise();
  }

  changeAdminPass(newPass: NewPass): Promise<any> {
    return this.http.post<any>(`${this.adminPassWordUrl}`, newPass).toPromise();
  }
  
  

  async changeName(user: User): Promise<JwtResponse> {
    return await  this.http.post<JwtResponse>(`${this.nameUrl}`, user).toPromise();
  }

  changeEmail(user: User): Promise<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.emailUrl}`, user).toPromise();
  }

  changeUsername(user: User): Promise<JwtResponse> {
    return this.http.post<JwtResponse>(`${this.usernameUrl}`, user).toPromise();
  }

  redirect() {
    return this.http.get(`${this.redirectUrl}`);
  }


}
