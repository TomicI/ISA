import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {Aviokompanija} from '../model';


@Injectable()
export class AviokompanijaService {
	public AVIO_API='//localhost:8080/api/aviokompanija';
public pom;
  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.AVIO_API );
  }
  
  getAviokompanija(id: number): Observable<Object> {
    console.log(this.AVIO_API+ '/' + id);
    return this.http.get(this.AVIO_API +'/'+id);
  }
  
  saveAviokompanija(aviokompanija: Object): Promise<Object>{
    console.log(aviokompanija);
    return this.http.post(this.AVIO_API, aviokompanija).toPromise();
  }

  deleteAviokompanija(id: Object): Observable<Object>{
    console.log("Brise se id: " + id);
    console.log(this.AVIO_API+ '/' + id);
    return this.http.delete(this.AVIO_API+ '/' + id);
  }

  updateAviokompanija(aviokompanija: Object): Promise<Object>{
    console.log(aviokompanija);
    return this.http.put(this.AVIO_API, aviokompanija).toPromise();
  }

  getLL(st: string): Observable<Object>{
     return this.http.get('https://api.mapbox.com/geocoding/v5/mapbox.places/'+ st+'.json?access_token=pk.eyJ1IjoiaXYzIiwiYSI6ImNqcmw4dTFkZzA1a2E0M280cmN5ZzB2azcifQ.onqhM8uoPAXAYdE9JRJX0g');
  }
}
