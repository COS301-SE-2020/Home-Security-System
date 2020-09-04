import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class VehicleService {

  private baseUrl = 'http://localhost:9000/springboot/api/vehicles';

  constructor(private http: HttpClient) { }

  getVehicleById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addVehicle(vehicle: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, vehicle);
  }

  updateVehicle(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteVehicle(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getVehicleList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  private handleError(error: Response)
  {
    return Observable.throw(error);
  }
}
