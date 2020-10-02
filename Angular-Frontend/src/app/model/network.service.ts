import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
// import {environment} from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class NetworkService {
  private baseUrl = `${environment.apiUrl}/api/networks`;
  constructor(private http: HttpClient) { }

  getAllNetworks(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
  getNetworkById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addNetwork(network: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, network);
  }

  editNetwork(id: number, value: any): Observable<any>{
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteNetwork(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

}
