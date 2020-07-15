import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { TitleService } from './title.service';
import { AngularFireDatabase, FirebaseListObservable } from 'angularfire2/database';
import { AngularFirestoreModule } from 'angularfire2/firestore';
//import Auth = firebase.auth.Auth;

class Book {
  constructor(public title, public authors) { }
}
class Authors{
  constructor(public name, public surname) {
  }
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  /*
  template:
      `
        <ul>
            <li *ngFor="let book of books | async">
                <pre>{{ book | json }}</pre>
            </li>
        </ul>
        <button (click)="AddBook()">Add a book!</button>
        <button (click)="filterBooks()">Filter books</button>
    `,
   */
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Angular-Frontend';
  public books: FirebaseListObservable<Book[]>;
  public authos: FirebaseListObservable<Authors[]>;
  constructor(private titleService: Title, private router: Router,
              private activatedRoute: ActivatedRoute, private db: AngularFireDatabase) {
    this.books = db.list('/books');
  }

  setDocTitle(title: string) {
    this.titleService.setTitle(title);
  }
  private bookCounter = 21;
  private filter = '';
  private auths = ['John', 'Snow'];


  public AddBook(): void {
    let anotherObject = {
      email: 'email@email.com',
      password: '1234'
    };
    let obj = {
      description: 'jello',
      name: 'ooof',
      object: anotherObject
    };
    let newBook = new Book(`My book #${this.bookCounter++}`, 0);
    this.books.push(obj);
    //let newAuthor = new Authors('ye', 'boi');
    //this.books.child('authors').push(newAuthor);

  }
  public filterBooks(): void {
    console.log(this.db.list('/books').snapshotChanges());
  }
  /*
  getBooks(){
    this.books = this.db.list('/books').snapshotChanges()
      .pipe(map(actions => actions.map(this.documentToDomainObject)));

    return this.books;
  }
*/
  ngOnInit() {
    const appTitle = this.titleService.getTitle();
    this.router
      .events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => {
        let child = this.activatedRoute.firstChild;
        while (child.firstChild) {
          child = child.firstChild;
        }
        if (child.snapshot.data.title) {
          return child.snapshot.data.title;
        }
        return appTitle;
      })
    ).subscribe((ttl: string) => {
      this.titleService.setTitle(ttl);
    });
  }
}
