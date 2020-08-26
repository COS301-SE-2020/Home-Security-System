import { Component, OnInit } from '@angular/core';
import {AppComponent} from '../../app.component';
import {from, Observable} from 'rxjs';
import {Notification} from '../../model/notification';
import {NotificationService} from '../../model/notification.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})

export class NotificationComponent implements OnInit {
  notification: Observable<Notification[]>;
  note: Notification;

  constructor(private notificationService: NotificationService, private SpinnerService: NgxSpinnerService,
              private appService: TitleService) { }

  reloadData() {
    this.note = new Notification();
    this.notification = this.notificationService.getNotificationList();
    setTimeout(() => {
      this.ngOnInit();
    }, 300000);
  }

  removeNotification(id: number) {
    this.SpinnerService.show();
    this.notificationService.getNotificationById(id)
      .subscribe(
      data => {
        // console.log(data);
        this.note = data;
        this.note.notificationDeleted = new Date();
        this.notificationService.updateNotification(id, this.note)
          .subscribe(value => {
            // console.log(value);
            setTimeout(() => {
              this.SpinnerService.hide();
            }, 500);
            this.reloadData();
          }, error => console.log(error));
      }, error => console.log(error));
  }

  ngOnInit(): void {
    this.appService.setTitle('Notifications');
    this.reloadData();
  }
}
