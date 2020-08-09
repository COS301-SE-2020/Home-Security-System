import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private baseUrl = 'http://localhost:8080/springboot/api/notifications';

  constructor(private http: HttpClient) { }

  getNotificationById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addNotification(notification: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, notification);
  }

  deleteNotification(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getNotificationList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  private handleError(error: Response)
  {
    return Observable.throw(error);
  }
}
