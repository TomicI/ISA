import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {Let} from '../model';

@Injectable()
export class LetService {

  public LET_API='//localhost:8080/api/let';
  public KARTA_API='//localhost:8080/api/karta';

  constructor(private http: HttpClient) { }
  
  getAll(): Observable<any> {
    return this.http.get(this.LET_API + '/lista');
  }
  
  getLet(id: number): Observable<Object> {
    console.log(this.LET_API+ '/' + id);
    return this.http.get(this.LET_API +'/'+id);
  }

  getKarta(id: number): Observable<Object> {
    console.log(this.KARTA_API+ '/' + id);
    return this.http.get(this.KARTA_API +'/'+id);
  }
  
  getLetove(id: number): Observable<any> {
    console.log(this.LET_API+ '/letovi/' + id);
    return this.http.get(this.LET_API +'/letovi/'+id);
  }

  saveLet(letC: Object): Promise<Object>{
    console.log(letC);
    return this.http.post(this.LET_API, letC).toPromise();
  }

  saveKarta(letC: Object): Promise<Object>{
    console.log(letC);
    return this.http.post(this.KARTA_API, letC).toPromise();
  }

  deleteLet(id: Object): Observable<Object>{
    console.log("Brise se id: " + id);
    console.log(this.LET_API+ '/' + id);
    return this.http.delete(this.LET_API+ '/' + id);
  }

  updateLet(letC: Object): Promise<Object>{
    console.log(letC);
    return this.http.put(this.LET_API, letC).toPromise();
  }

  updateKarta(letC: Object): Promise<Object>{
    console.log(letC);
    return this.http.put(this.KARTA_API, letC).toPromise();
  }

  zauzmiSediste(letC: Object): Promise<Object>{
    return this.http.put(this.LET_API+'/sediste', letC).toPromise();
  } 

  pretraga(letP: Object): Promise<any> {
    return this.http.put(this.LET_API + '/pretraga/', letP).toPromise();
  }
}
