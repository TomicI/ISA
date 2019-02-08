import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class MapService {

  accessToken = 'pk.eyJ1IjoiaXYzIiwiYSI6ImNqcmw4dTFkZzA1a2E0M280cmN5ZzB2azcifQ.onqhM8uoPAXAYdE9JRJX0g';

  constructor(private http: HttpClient) { }

  getLL(st: string): Observable<Object> {
    return this.http.get('https://api.mapbox.com/geocoding/v5/mapbox.places/' + st + '.json?access_token=' + this.accessToken);
  }

}
