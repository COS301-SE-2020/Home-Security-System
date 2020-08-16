import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ImageService {
  private baseUrl = 'http://localhost:8080/springboot/api/images';

  constructor(private http: HttpClient) { }

  getImageById(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  addImage(image: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, image);
  }

  updateImage(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  deleteImage(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getImageList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  private handleError(error: Response)
  {
    return Observable.throw(error);
  }
}
