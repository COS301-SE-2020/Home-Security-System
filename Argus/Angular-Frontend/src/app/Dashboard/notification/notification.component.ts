import { Component, OnInit } from '@angular/core';
import {AngularFireDatabase, FirebaseListObservable} from 'angularfire2/database';
import {AngularFirestoreDocument, AngularFirestore, AngularFirestoreCollection} from 'angularfire2/firestore';
import {ActivatedRoute} from '@angular/router';
import {Observable, of} from 'rxjs';

class BookComp {
  constructor(public title, public authors) { }
}
@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})


export class NotificationComponent implements OnInit {
  public books: FirebaseListObservable<BookComp[]>;
  items: FirebaseListObservable<any>;
  public d = new Date();
  public t = this.d.getTime();

  constructor(private db: AngularFireDatabase/*, private firestore: AngularFirestore*/) {
    this.books = db.list('/users');
  }
  public AddBook(): void {

    const anotherObject = {
      email: 'email@email.com',
      password: '1234'
    };

    const obj = {
      userID: this.t,
      name: 'GG',
      surname: 'EZ',
      email: 'email@email.com',
      password: 'password',
      details: anotherObject
    };
    //const newBook = new BookComp(`My book #${this.bookCounter++}`, 0);
    this.books.push(obj);

  }
  ReadDB(): void{
    let users = this.db.database.ref('users/-MCIKAXWtOexVlx9uDJJ');
    users.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        console.log('The ' + data.key.toString() + ' values are ' + data.val());
      });
    });
  }


    //console.log(this.firestore.collection('users').snapshotChanges());
    //this.items = this.db.database.ref('users');
    //console.log(this.items);
    /*
    this.items = this.db.database.ref('/users');
    for (let element in this.items) {
      console.log(element);
    }
     */
    /*
    this.items.forEach(element => {
      console.log(element);
    });
     */

  /*
  public ReadDB(): void{
    this.items = this.db.list('/users');
    this.items.of(value=> {
      console.log(this.items);
    });
  }
   */
  ngOnInit() {
  }

}
