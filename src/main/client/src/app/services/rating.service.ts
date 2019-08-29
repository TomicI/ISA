import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  private RatingURL = 'http://localhost:8080/api/ocena';

  constructor(private http: HttpClient) { }

  saveRentACarRating(ratings: Object): Observable<any> {
    return this.http.post(`${this.RatingURL}/rentACar`,ratings );
  }
  getRentACarRating(ratings: Object): Observable<any> {
    return this.http.get(`${this.RatingURL}/rentACar`,ratings );
  }
}
