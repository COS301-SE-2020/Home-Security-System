import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';

import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {snapshotChanges} from '@angular/fire/database';

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  public usersList: AngularFireList<any>;

  title: String;

  constructor(private appService: TitleService, private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }

  ReadDB(): void {
    const uL = this.db.database.ref('users');
    let obj = null;
    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if (data.val().u_id === '657870656e6461626c657340676d61696c2e636f6d') {
          obj = data.val();
        }
      });
      if (obj === null) {
        console.log('error');
      } else {
        const userImageDisplay = document.getElementById('profilePic') as HTMLImageElement;
        userImageDisplay.src = obj.profilePicture;
      }
    });
  }

  ngOnInit(): void {
    this.appService.getTitle().subscribe(appTitle => this.title = appTitle);
    this.ReadDB();
  }

}
