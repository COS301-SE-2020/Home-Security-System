import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})
export class TitleService {
    private title = new BehaviorSubject<String>('App title');
    private title$ = this.title.asObservable();
  
  constructor() { }

  setTitle(title: String) {
    this.title.next(title);
  }

  getTitle(): Observable<String> {
    return this.title$;
  }
}