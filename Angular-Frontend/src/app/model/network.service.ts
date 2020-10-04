import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
// import {environment} from '../../environments/environment.prod';

const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('Sigma' + ':' + 'COS301Sigma!') });

@Injectable({
  providedIn: 'root'
})
export class NetworkService {
  private baseUrl = `${environment.apiUrl}/api/networks`;

  constructor(private http: HttpClient) { }

  getAllNetworks(): Observable<any> {
    return this.http.get(`${this.baseUrl}`,{headers});
  }
  getNetworkById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`,{headers});
  }

  addNetwork(network: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, network,{headers});
  }

  editNetwork(id: number, value: any): Observable<any>{
    return this.http.put(`${this.baseUrl}/${id}`, value,{headers});
  }

  deleteNetwork(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

}
