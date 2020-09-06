import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private baseUrl = 'http://localhost:9000/springboot/api/images';

  constructor(private http: HttpClient) { }

  getImageById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addImage(image: any): Observable<any> {
    return this.http.post(`${this.baseUrl}`, image);
  }

  updateImage(id: number, value: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteImage(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getImageList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}
