import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs/index';

@Injectable({
  providedIn: 'root'
})
export class TitleService {
    private title = new BehaviorSubject<string>('App title');
    private title$ = this.title.asObservable();

  constructor() { }

  setTitle(title: string) {
    this.title.next(title);
  }

  getTitle(): Observable<string> {
    return this.title$;
  }
}
