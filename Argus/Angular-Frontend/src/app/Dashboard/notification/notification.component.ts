import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../app.component';
import {from} from 'rxjs';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})


export class NotificationComponent implements OnInit {
  public userDetail;
  public d = new Date();
  public t = this.d.getTime();

  constructor() {}

  public AddDB(): void {}

  ReadDB(): void{}

  DeleteDB(): void{}

  UpdateDB(): void{}

  clearTable(): void{
    const table = document.getElementById('notificationsTable') as HTMLTableElement;
    const rows = table.rows.length;
    console.log('len: ' + rows);
    for (let i = 1; i < rows; i++){
      table.deleteRow(1);
    }
  }

  AddNotification(type, messageN): void{
    this.clearTable();
  }
  public hideNotification(uid, notNumb): void{
  }

  ReadDBNotiications(): void{}


  ngOnInit() {
    this.clearTable();
    this.ReadDBNotiications();
  }

}
