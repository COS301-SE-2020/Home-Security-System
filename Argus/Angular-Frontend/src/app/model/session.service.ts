import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
// import {environment} from '../../environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private baseUrl = `${environment.apiUrl}/springboot/sessions`;

  constructor(private http: HttpClient) {
  }

  getSessionDetails(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getSessionDetails`);
  }

  getAllEmails(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllEmails`);
  }

  addToSession(session: any) {
    return this.http.post(`${this.baseUrl}/addSession`, session);
  }

  deleteSessionById(id: string) {
    return this.http.delete(`${this.baseUrl}/deleteSessionId/${id}`);
  }
}
