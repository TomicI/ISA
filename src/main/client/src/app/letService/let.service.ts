import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {Karta, Let, Lokacija, Sediste, Segment} from '../model';

@Injectable()
export class LetService {

  public LET_API='//localhost:8080/api/let';
  public LOK_API='//localhost:8080/api/lokacija';
  public KAR_API='//localhost:8080/api/karta';
  public KONF_API='//localhost:8080/api/konfiguracijaLeta';
  public SEGMENT_API='//localhost:8080/api/segment';

  constructor(private http: HttpClient) { }
  
  getAll(): Promise<Let[]> {
    return this.http.get<Let[]>(this.LET_API + '/lista').toPromise();
  }
  
  getLet(id: number): Promise<Let> {
    console.log(this.LET_API+ '/' + id);
    return this.http.get<Let>(this.LET_API +'/'+id).toPromise();
  }
  
  getLetove(id: number): Promise<Let[]> {
    console.log(this.LET_API+ '/letovi/' + id);
    return this.http.get<Let[]>(this.LET_API +'/letovi/'+id).toPromise();
  }

  saveLet(letC: Let): Promise<Let>{
    console.log(letC);
    return this.http.post<Let>(this.LET_API, letC).toPromise();
  }

  deleteLet(id: number): Promise<Let>{
    console.log("Brise se id: " + id);
    console.log(this.LET_API+ '/' + id);
    return this.http.delete<Let>(this.LET_API+ '/' + id).toPromise();
  }

  updateLet(letC: Let): Promise<Let>{
    console.log(letC);
    return this.http.put<Let>(this.LET_API, letC).toPromise();
  }

  getAllLokacije(): Promise<Lokacija[]> {
    return this.http.get<Lokacija[]>(this.LOK_API).toPromise();
  }

  getLokacija(id: number): Promise<Lokacija> {
    return this.http.get<Lokacija>(this.LOK_API+'/'+id).toPromise();
  }

  pretraga(pom: Let): Promise<Let[]> {
    return this.http.put<Let[]>(this.LET_API +'/pretraga', pom).toPromise();
  }

  saveKarta(sedsta: number[]): Promise<Karta>{
    return this.http.post<Karta>(this.KAR_API, sedsta).toPromise();
  }

  getSegmente(idKonfiguracije: number): Promise<Segment[]>{
    return this.http.get<Segment[]>(this.KONF_API+'/'+idKonfiguracije+'/segmenti').toPromise();
  }

  getSedista(idSegment: number, idL:number): Promise<Sediste[]>{
    return this.http.get<Sediste[]>(this.SEGMENT_API+'/'+idSegment+'/sedista/'+ idL).toPromise();
  }
}
