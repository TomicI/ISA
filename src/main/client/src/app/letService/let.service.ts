import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {Karta, Let, Lokacija, Putnik, Rezervacija, Sediste, Segment} from '../model';

@Injectable()
export class LetService {

  public LET_API='//localhost:8080/api/let';
  public LOK_API='//localhost:8080/api/lokacija';
  public KAR_API='//localhost:8080/api/karta';
  public KONF_API='//localhost:8080/api/konfiguracijaLeta';
  public SEGMENT_API='//localhost:8080/api/segment';
  public PUTNIK_API='//localhost:8080/api/putnik';
  public SEDISTA_API='//localhost:8080/api/sedista';
  public REZ_API='//localhost:8080/api/rezervacija';

  constructor(private http: HttpClient) { }
  
  getAll(): Promise<Let[]> {
    return this.http.get<Let[]>(this.LET_API).toPromise();
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

  saveKarta(sedsta: Sediste[]): Promise<Rezervacija>{
    return this.http.post<Rezervacija>(this.KAR_API, sedsta).toPromise();
  }

  getKarta(id: number): Promise<Karta>{
    return this.http.get<Karta>(this.KAR_API+'/'+id).toPromise();
  }

  getSedistaKarta(kartaId: number): Promise<Sediste[]>{
    return this.http.get<Sediste[]>(this.KAR_API+'/sedista/'+kartaId).toPromise();
  }

  getSegmente(idKonfiguracije: number): Promise<Segment[]>{
    return this.http.get<Segment[]>(this.KONF_API+'/'+idKonfiguracije+'/segmenti').toPromise();
  }

  getSedista(idL:number): Promise<Sediste[]>{
    return this.http.get<Sediste[]>(this.LET_API+'/sedista/'+ idL).toPromise();
  }

  getAllSegmente(): Promise<Segment[]>{
    return this.http.get<Segment[]>(this.SEGMENT_API).toPromise();
  }


  savePutnik(putnik: Putnik, id:number): Promise<Putnik>{
    return this.http.post<Putnik>(this.PUTNIK_API+'/'+id, putnik).toPromise();
  }

  getSediste(id:number): Promise<Sediste>{
    return this.http.get<Sediste>(this.SEDISTA_API+'/'+ id).toPromise();
  }

  getRez(id: number): Promise<Rezervacija>{
    return this.http.get<Rezervacija>(this.REZ_API+'/rez/'+id).toPromise();
  }

  brzaRezervacija(sedste: number, popust: number): Promise<Karta>{
    return this.http.post<Karta>(this.KAR_API+'/brzaRez/'+sedste, popust).toPromise();
  }

  deleteRezervacija(id: number): Promise<Object>{
    return this.http.delete(this.KAR_API+'/'+id).toPromise();
  }

  getBrzeRezervacije(id: number): Promise<Karta[]>{
    return this.http.get<Karta[]>(this.KAR_API+'/brze_rezervacije/'+id).toPromise();
  }

  saveBrzaRezervacija(karta: Karta): Promise<Rezervacija>{
    return this.http.post<Rezervacija>(this.KAR_API+'/rezervisanje', karta).toPromise();
  }
}
