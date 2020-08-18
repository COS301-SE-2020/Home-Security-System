import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private baseUrl = 'http://localhost:8080/springboot/api/notifications';

  constructor(private http: HttpClient) { }

  getNotificationById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addNotification(notification: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, notification);
  }

  updateNotification(id: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteNotification(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getNotificationList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
