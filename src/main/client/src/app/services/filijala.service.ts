import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Filijala, Vozilo } from '../model';

@Injectable()
export class FilijalaService {

  private FilijalaURL = 'http://localhost:8080/api/filijala';

  constructor(private http: HttpClient) { }

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

  async updateFilijala(filijala: Object): Promise<Object>{
    console.log('PUT');
    return await this.http.put(`${this.FilijalaURL}`, filijala).toPromise();
  }
}
