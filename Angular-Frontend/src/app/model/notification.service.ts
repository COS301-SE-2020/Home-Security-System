import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';
// import {environment} from '../../environments/environment.prod';

const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa('Sigma' + ':' + 'COS301Sigma!') });

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private baseUrl = `${environment.apiUrl}/api/notifications`;

  constructor(private http: HttpClient) { }

  getNotificationById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`,{headers});
  }

  addNotification(notification: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, notification,{headers});
  }

  updateNotification(id: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value,{headers});
  }

  deleteNotification(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getNotificationList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`,{headers});
  }
}
