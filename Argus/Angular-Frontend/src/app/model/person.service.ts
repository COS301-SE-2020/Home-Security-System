import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private baseUrl = `${environment.apiUrl}/springboot/api/people`;

  constructor(private http: HttpClient) { }

  getPersonById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addPerson(person: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, person);
  }

  updatePerson(id: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deletePerson(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getPersonList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
