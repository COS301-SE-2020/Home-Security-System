import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class EmailService {
  private baseUrl = 'http://localhost:8080/springboot/sendmail';

  constructor(private http: HttpClient) { }

  sendEmail(email: string){
    return this.http.get(`${this.baseUrl}/${email}`);
  }
}
