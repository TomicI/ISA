import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private RatingURL = 'http://localhost:8080/api/ocena';

  constructor(private http: HttpClient) { }

  getPermission(params): Observable<any> {

    return this.http.get(`${this.RatingURL}/permissionRent`,{ params: params } );
  }

  saveRentACarRating(ratings: Object): Observable<any> {
    return this.http.post(`${this.RatingURL}/rentACar`,ratings );
  }
  getRating(params): Observable<any> {

    return this.http.get(`${this.RatingURL}/ratings`,{ params: params } );
  }

  getAllRating(params):Observable<any> {

    return this.http.get(`${this.RatingURL}/allRating`,{ params: params } );
  }

}
