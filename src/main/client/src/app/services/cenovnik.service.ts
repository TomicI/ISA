import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CenovnikService {

  private CenovnikURL = 'http://localhost:8080/api/cenovnikrent';

  constructor(private http: HttpClient) { }

  async saveCenovnik(cenovnik: Object): Promise<Object> {

    return await this.http.post(`${this.CenovnikURL}`, cenovnik).toPromise();
  }

  async updateCenovnik(cenovnik: Object): Promise<Object> {
    return await this.http.put(`${this.CenovnikURL}`, cenovnik).toPromise();
  }

  async removeCenovnik(id: Number): Promise<Object> {
    return this.http.delete(`${this.CenovnikURL}/${id}`).toPromise();
  }






}
