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
  note: Notification;

  constructor(private notificationService: NotificationService, private appService: TitleService) {
  }

  reloadData() {
    this.note = new Notification();
    this.notification = this.notificationService.getNotificationList();
  }

  removeNotification(id: number) {
    this.notificationService.getNotificationById(id)
      .subscribe(
      data => {
        // console.log(data);
        this.note = data;
        this.note.notificationDeleted = new Date();
        this.notificationService.updateNotification(id, this.note)
          .subscribe(value => {
            // console.log(value);
          }, error => console.log(error));
        this.reloadData();
      },
      error => console.log(error));
  }

  ngOnInit(): void {
    this.appService.setTitle('Notifications');
    this.reloadData();
  }
}
