import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../app.component';
import {from, Observable} from 'rxjs';
import {Notification} from '../../model/notification';
import {NotificationService} from '../../model/notification.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})


export class NotificationComponent implements OnInit {
  notification: Observable<Notification[]>;

  constructor(private notificationService: NotificationService, private appService: TitleService) {
  }

  reloadData() {
    this.notification = this.notificationService.getNotificationList();
    this.notificationService.getNotificationList()
      .subscribe(
        data => {
          console.log(data);
        },
        error => console.log(error));
  }

  removeNotification(id: number) {
    this.notificationService.deleteNotification(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  addNotification(message: string){

  }

  ngOnInit(): void {
    this.appService.setTitle('Black List');
    this.reloadData();
  }
}
