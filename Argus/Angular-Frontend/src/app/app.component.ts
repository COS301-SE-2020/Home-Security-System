import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { TitleService } from './title.service';
import { AngularFirestoreModule } from 'angularfire2/firestore';
import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';


class usersListClass{
  constructor(public email, public passw) {
  }
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  public static UserKey: object = {};

  title = 'Angular-Frontend';
  public usersList: AngularFireList<any>;
  constructor(private titleService: Title, private router: Router,
              private activatedRoute: ActivatedRoute, private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }

  setDocTitle(title: string) {
    this.titleService.setTitle(title);
  }

  FindUser(name: string, password: string){
    let uL = this.db.database.ref('users');
    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if(data.val().name === name){
          AppComponent.UserKey = data.val();
        }
      });
    });
    return AppComponent.UserKey;
  }



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
