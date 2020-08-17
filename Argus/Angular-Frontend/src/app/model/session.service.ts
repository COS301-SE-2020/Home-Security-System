import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SessionService {
  private baseUrl = 'http://localhost:8080/springboot/sessions';

  constructor(private http: HttpClient) {
  }

  getSessionDetails(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getSessionDetails`);
  }

  getAllEmails(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllEmails`);
  }

  addToSession(id: string, email: string, password: string, role: string) {
    return this.http.get(`${this.baseUrl}/addSession/${id}/${email}/${password}/${role}`);
  }

  deleteSessionById(id: string) {
    return this.http.get(`${this.baseUrl}/deleteSessionId/${id}`);
  }
}
