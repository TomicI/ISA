import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {Aerodrom} from '../model';

@Injectable()
export class AerodromSService {
  aerodrom: Aerodrom;
  public AVIO_API='//localhost:8080/api/aerodrom';

  constructor(private http: HttpClient) { }

  getAllAerodromi(): Observable<any> {
    return this.http.get(this.AVIO_API + '/lista');
  }
  getAerodrom(id: number): Observable<Object> {
    console.log(this.AVIO_API+ '/' + id);
    return this.http.get(this.AVIO_API +'/'+id);
  }

 /* getAerodromIme(id: number): String {
    this.getAerodrom(id).subscribe(aerodrom => {
      this.aerodrom=aerodrom;
      console.log("preuzela " +this.aerodrom.nazivAerodroma + "      " + this.aerodrom. id + "  " + id);
      return this.aerodrom.nazivAerodroma;
    })
    
  }
*/
  saveAerodrom(aerodrom: Object): Promise<Object>{
    console.log(aerodrom);
    return this.http.post(this.AVIO_API, aerodrom).toPromise();
  }

  deleteAerodrom(id: Object): Observable<Object>{
    console.log("Brise se id: " + id);
    console.log(this.AVIO_API+ '/' + id);
    return this.http.delete(this.AVIO_API+ '/' + id);
  }

  updateAerodrom(aerodrom: Object): Promise<Object>{
    console.log(aerodrom);
    return this.http.put(this.AVIO_API, aerodrom).toPromise();
  }




}
