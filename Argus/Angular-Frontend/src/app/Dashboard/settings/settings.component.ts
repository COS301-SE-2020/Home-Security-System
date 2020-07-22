import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';

import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {snapshotChanges} from '@angular/fire/database';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit
{
  public usersList: AngularFireList<any>;
  constructor(private appService: TitleService, private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }

  ReadDB(): void {
    const uL = this.db.database.ref('users');
    let obj = null;
    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if (data.val().uniqueId === '321') {
          obj = data.val();
        }
      });
      if (obj === null) {
        console.log('error');
      } else {
        const emails = document.getElementById('emailSlider') as HTMLInputElement;
        if (obj.notificationsEmail === 'yes') {
          emails.checked = true;
        } else {
          emails.checked = true;
        }

        const locals = document.getElementById('localSlider') as HTMLInputElement;
        if (obj.obj.notificationsLocal === 'yes') {
          locals.checked = true;
        } else {
          locals.checked = false;
        }
      }
    });
  }

  UpdateDB(): void {
    const uL = this.db.database.ref('users');
    let obj = null;

    const localNoti = document.getElementById('localSlider') as HTMLInputElement;
    const emailNoti = document.getElementById('emailSlider') as HTMLInputElement;

    function returnSettingValEmail(){
      const wantsEmails = document.getElementById('emailSlider') as HTMLInputElement;

      if ( wantsEmails.checked === true) {
        return 'yes';
      }
      else {
        return 'no';
      }
    }

    function returnSettingValLocal(){
      const wantsLocals = document.getElementById('localSlider') as HTMLInputElement;

      if ( wantsLocals.checked === true) {
        return 'yes';
      }
      else {
        return 'no';
      }
    }

    const locals = returnSettingValLocal();
    const emails = returnSettingValEmail();

    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if (data.val().uniqueId === '123') {
          obj = data.val();
        }
      });
    });
    this.usersList.update( '-MCnFvdHyMQGBNi1w-Ci' , { notificationsLocal: locals , notificationsEmail: emails } );
  }

  ngOnInit(): void {
    this.appService.setTitle('Settings');
    this.ReadDB();
  }
}
