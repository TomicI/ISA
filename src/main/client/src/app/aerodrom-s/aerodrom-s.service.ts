import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {Aerodrom} from '../model';
import {promise} from "selenium-webdriver";

@Injectable()
export class AerodromSService {
  aerodrom: Aerodrom;
  public AVIO_API='//localhost:8080/api/aerodrom';

  constructor(private http: HttpClient) { }

  getAllAerodromi(): Promise<Aerodrom[]> {
    return this.http.get<Aerodrom[]>(this.AVIO_API ).toPromise();
  }
  getAerodrom(id: number): Promise<Aerodrom> {
    console.log(this.AVIO_API+ '/' + id);
    return this.http.get<Aerodrom>(this.AVIO_API +'/'+id).toPromise();
  }

 /* getAerodromIme(id: number): String {
    this.getAerodrom(id).subscribe(aerodrom => {
      this.aerodrom=aerodrom;
      console.log("preuzela " +this.aerodrom.nazivAerodroma + "      " + this.aerodrom. id + "  " + id);
      return this.aerodrom.nazivAerodroma;
    })
    
  }
*/
  saveAerodrom(aerodrom: Object): Promise<Aerodrom>{
    console.log(aerodrom);
    return this.http.post<Aerodrom>(this.AVIO_API, aerodrom).toPromise();
  }

  deleteAerodrom(id: Aerodrom): Promise <Aerodrom>{
    console.log("Brise se id: " + id);
    console.log(this.AVIO_API+ '/' + id);
    return this.http.delete<Aerodrom>(this.AVIO_API+ '/' + id).toPromise();
  }

  updateAerodrom(aerodrom: Aerodrom): Promise<Aerodrom>{
    console.log(aerodrom);
    return this.http.put<Aerodrom>(this.AVIO_API, aerodrom).toPromise();
  }




}
