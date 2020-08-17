import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class BackendsessionService {
  private baseUrl = 'http://localhost:8080/springboot/sessions';

  constructor(private http: HttpClient) {
  }

  getAllDetails(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllDetails`);
  }
  getAllEmails(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getEmails`);
  }
  addToSession(id: string, email: string, password: string, role: string){
    return this.http.get(`${this.baseUrl}/addSession/${id}/${email}/${password}/${role}`);
  }
  deleteSessionById(id: string){
    return this.http.get(`${this.baseUrl}/removeById/${id}`);
  }
}
