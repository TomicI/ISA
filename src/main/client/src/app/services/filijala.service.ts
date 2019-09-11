import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Observable } from 'rxjs';

import { Filijala, Vozilo } from '../model';

@Injectable()
export class FilijalaService {

  private FilijalaURL = 'http://localhost:8080/api/filijala';

  constructor(private http: HttpClient) { }

  getAll():Observable<any>{
    return this.http.get<any>(`${this.FilijalaURL}/all`);
  }

  getVozilaTabela(id: Number): Observable<any[]> {
    return this.http.get<any[]>(`${this.FilijalaURL}/${id}/vozila`);
  }

  async saveFilijala(filijala: Object): Promise<Object> {
    console.log(filijala);
    console.log('POST');
    return this.http.post(`${this.FilijalaURL}`, filijala).toPromise();
  }

  getAllVozila(id: number): Observable<any> {
    return this.http.get(`${this.FilijalaURL}/${id}/vozila`);
  }
  async removeFilijala(id: number): Promise<Object> {
    return await this.http.delete(`${this.FilijalaURL}/${id}`).toPromise();
  }

  async updateFilijala(filijala: Object): Promise<Object> {
    console.log('PUT');
    return await this.http.put(`${this.FilijalaURL}`, filijala).toPromise();
  }

  getallRez(): Observable<any>{
    return this.http.get(`${this.FilijalaURL}/rezadmin`);
  }

  getIncome(params):Observable<any>{

    let param = new HttpParams();

    param = param.append('from',params.from);
    param = param.append('to',params.to);

    return this.http.get(`${this.FilijalaURL}/income`,{params:param});
  }

  getDaily(params):Observable<any>{

    let param = new HttpParams();

    param = param.append('pick',params.pick);
    param = param.append('opt',params.opt);

    return this.http.get(`${this.FilijalaURL}/dailyVeh`,{params:param});
  }

}
