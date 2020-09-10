import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private baseUrl = `${environment.apiUrl}/springboot/api/vehicles`;

  constructor(private http: HttpClient) { }

  getVehicleById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addVehicle(vehicle: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, vehicle);
  }

  updateVehicle(id: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteVehicle(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getVehicleList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
