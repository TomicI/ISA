import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private VehicleURL = 'http://localhost:8080/api/vozilo';

  constructor(private http: HttpClient) { }

  async saveVehicle(vehicle: Object): Promise<Object> {
   return await this.http.post(`${this.VehicleURL}`, vehicle).toPromise();
  }

  async updateVehicle(vehicle: Object): Promise<Object> {
    return await this.http.put(`${this.VehicleURL}`, vehicle).toPromise();
  }

  async removeVehicle(id: number): Promise<Object> {
    return await this.http.delete(`${this.VehicleURL}/${id}`).toPromise();
  }



}
