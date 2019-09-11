import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import {
  Aerodrom,
  Aviokompanija,
  DodatnaUslugaAviokompanija, KategorijaSedista,
  KonfiguracijaLeta,
  Let,
  Lokacija,
  Prtljag,
  Segment
} from '../model';


@Injectable()
export class AviokompanijaService {
	public AVIO_API='//localhost:8080/api/aviokompanija';
	public SEGMENT_API='//localhost:8080/api/segment';
	public KONF_API='//localhost:8080/api/konfiguracijaLeta';
	public KAT_API='//localhost:8080/api/kategorijaSedista';
public pom;
  constructor(private http: HttpClient) { }
  
  getAll(): Promise<Aviokompanija[]> {
    return this.http.get<Aviokompanija[]>(this.AVIO_API ).toPromise();
  }
  
  getAviokompanija(id: number): Promise<Aviokompanija> {
    console.log(this.AVIO_API+ '/' + id);
    return this.http.get<Aviokompanija>(this.AVIO_API +'/'+id).toPromise();
  }

  getAviokompanijaAdmin(): Promise<Aviokompanija> {
    return this.http.get<Aviokompanija>(this.AVIO_API +'/admin').toPromise();
  }


  saveAviokompanija(aviokompanija: Aviokompanija): Promise<Aviokompanija>{
    console.log(aviokompanija);
    return this.http.post<Aviokompanija>(this.AVIO_API, aviokompanija).toPromise();
  }

  deleteAviokompanija(id: Object): Promise<Aviokompanija>{
    console.log("Brise se id: " + id);
    console.log(this.AVIO_API+ '/' + id);
    return this.http.delete<Aviokompanija>(this.AVIO_API+ '/' + id).toPromise();
  }

  updateAviokompanija(aviokompanija: Object): Promise<Aviokompanija>{
    console.log(aviokompanija);
    return this.http.put<Aviokompanija>(this.AVIO_API, aviokompanija).toPromise();
  }

  getLL(st: string): Observable<Object>{
     return this.http.get('https://api.mapbox.com/geocoding/v5/mapbox.places/'+ st+'.json?access_token=pk.eyJ1IjoiaXYzIiwiYSI6ImNqcmw4dTFkZzA1a2E0M280cmN5ZzB2azcifQ.onqhM8uoPAXAYdE9JRJX0g');
  }

  getAerodorome(id: number): Promise<Aerodrom[]>{
    return this.http.get<Aerodrom[]>(this.AVIO_API+'/'+id+'/aerodromi').toPromise();
  }

  createAerodorom(id: number, aerodrom: Aerodrom): Promise<Aerodrom>{
    return this.http.post<Aerodrom>(this.AVIO_API+'/'+id+'/aerodrom', aerodrom).toPromise();
  }

  getDestinacije(id: number): Promise<Lokacija[]>{
    return this.http.get<Lokacija[]>(this.AVIO_API+'/'+id+'/destinacije').toPromise();
  }

  getPrtljag(id: number): Promise<Prtljag[]>{
    return this.http.get<Prtljag[]>(this.AVIO_API+'/'+id+'/prtljag').toPromise();
  }

  createPrtljag(id: number, prtljag:Prtljag): Promise<Prtljag>{
    return this.http.post<Prtljag>(this.AVIO_API+'/'+id+'/prtljag', prtljag).toPromise();
  }

  getLetovi(id: number): Promise<Let[]>{
    return this.http.get<Let[]>(this.AVIO_API+'/'+id+'/letovi').toPromise();
  }

  getKonfiguracije(id: number): Promise<KonfiguracijaLeta[]>{
    return this.http.get<KonfiguracijaLeta[]>(this.AVIO_API+'/'+id+'/konfiguracije').toPromise();
  }

  getKonfiguracija(id: number): Promise<KonfiguracijaLeta>{
    return this.http.get<KonfiguracijaLeta>(this.KONF_API+'/'+id).toPromise();
  }

  getKategorija(id: number): Promise<KategorijaSedista>{
    return this.http.get<KategorijaSedista>(this.KAT_API+'/'+id).toPromise();
  }

  getKategorije(): Promise<KategorijaSedista[]>{
    return this.http.get<KategorijaSedista[]>(this.KAT_API).toPromise();
  }

  createKonfiguracija(id: number, konfiguracija:KonfiguracijaLeta): Promise<KonfiguracijaLeta>{
    return this.http.post<KonfiguracijaLeta>(this.AVIO_API+'/'+id+'/konfiguracija', konfiguracija).toPromise();
  }

  createKategorija(kategorija:KategorijaSedista): Promise<KategorijaSedista>{
    return this.http.post<KategorijaSedista>(this.KAT_API, kategorija).toPromise();
  }

  getDodatneUsluge(id: number): Promise<DodatnaUslugaAviokompanija[]>{
    return this.http.get<DodatnaUslugaAviokompanija[]>(this.AVIO_API+'/'+id+'/dodatne_usluge').toPromise();
  }

  setLocation(id: number, location: Lokacija): Promise<Lokacija>{
    return this.http.put<Lokacija>(this.AVIO_API+'/'+id+'/postavi_lokaciju/',location).toPromise();
  }

  addSegment(id:number, letC: Segment): Promise<Segment>{
    return this.http.post<Segment>(this.SEGMENT_API+'/'+id, letC).toPromise();
  }

  getLetoveAdmin(): Promise<Let[]> {
    return this.http.get<Let[]>(this.AVIO_API +'/admin/letovi/').toPromise();
  }
}
