import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {Notification} from '../../model/notification';
import {NotificationService} from '../../model/notification.service';
import {TitleService} from '../../title.service';
import {NgxSpinnerService} from 'ngx-spinner';
import {AuthService} from "../../model/auth.service";
import {SessionClass} from "../../model/session";

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})

export class NotificationComponent implements OnInit {
  notification: Observable<Notification[]>;
  note: Notification;
  info: SessionClass = this.authService.retrieveUserInfo();

  constructor(private notificationService: NotificationService, private authService: AuthService,
              private SpinnerService: NgxSpinnerService, private appService: TitleService) { }

  reloadData() {
    this.note = new Notification();
    this.notification = this.notificationService.getNotificationList();
    /*setTimeout(() => {
      this.ngOnInit();
    }, 300000);*/
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
            .subscribe(() => {
              setTimeout(() => {
                this.SpinnerService.hide();
                this.reloadData();
              }, 500);
            });
        });
  }

  imageClick(id): void {
    const modal = document.getElementById('myModal') as HTMLElement;
    const img = document.getElementById('noteImg' + id) as HTMLImageElement;
    const modalImg = document.getElementById('img01') as HTMLImageElement;
    // const captionText = document.getElementById('caption') as HTMLElement;
    modal.style.display = 'block';
    modalImg.src = img.src;
    // captionText.innerHTML = img.alt;
    const notificationBar = document.getElementById('NotDiv') as HTMLElement;
    document.getElementById('navBars').style.visibility = 'hidden';
    notificationBar.style.visibility = 'hidden';
    // const span = document.getElementsByClassName('close')[0];

  }
  modalClick(): void{
    const notificationBar = document.getElementById('NotDiv') as HTMLElement;
    document.getElementById('navBars').style.visibility = 'visible';
    notificationBar.style.visibility = 'visible';
    const modal = document.getElementById('myModal') as HTMLElement;
    modal.style.display = 'none';
  }

  ngOnInit(): void {
    this.appService.setTitle('Notifications');
    this.reloadData();
    //this.deleteOld(1);
  }

  deleteAll() {
    let counter = 0;
    this.SpinnerService.show();
    this.notificationService.getNotificationList()
      .subscribe(data => {
        while (data[counter] != null) {
          this.note = data[counter];
          this.note.notificationDeleted = new Date();
          this.notificationService.updateNotification(data[counter].notificationId, this.note).subscribe();
          counter++;
        }
        setTimeout(() => {
          this.SpinnerService.hide();
          this.reloadData();
        }, 10000);
      });
  }

  deleteOld(option: number) {
    let counter = 0;
    const today = new Date();
    const year = today.getFullYear();
    const month = ((today.getMonth() + 1) >= 10) ? (today.getMonth() + 1) : '0' + (today.getMonth() + 1);
    const day = today.getDate();

    this.notificationService.getNotificationList()
      .subscribe(data => {
          while (data[counter] != null) {
            let num = 0;
            // console.log(data);
            const temp = data[counter].onDate;
            if (temp != null) {
              const tempYear = temp.substr(0, 4);
              const tempMonth = temp.substr(5, 2);
              const tempDay = temp.substr(8, 2);
              if (option === 1) {
                if (tempYear === year.toString()) {
                  const x = Number(tempMonth) + 1;
                  const y = Number(month) + 1;
                  if (tempMonth === month || x === y) {
                    num = this.getDay(Number(tempMonth), Number(tempDay));
                    if (num === day) {
                      this.notificationService.deleteNotification(data[counter].notificationId).subscribe();
                    }
                  }
                }
              }
              else if (option === 2) {
                if (tempYear === year.toString()) {
                  if (Number(tempMonth) < Number(month)) {
                    if (Number(tempDay) === day && Number(tempDay) < 28) {
                      this.notificationService.deleteNotification(data[counter].notificationId).subscribe();
                    }
                    else if (Number(tempDay) === 28) {
                      this.notificationService.deleteNotification(data[counter].notificationId).subscribe();
                    }
                  }
                }
              }
              else if (option === 3) {
                if (Number(tempYear) < year) {
                  if (Number(tempMonth) === Number(month)) {
                    this.notificationService.deleteNotification(data[counter].notificationId).subscribe();
                  }
                }
              }
            }
            counter++;
          }
        });
  }

  getDay(month: number, day: number): number {
    let num = 0;
    let temp = 0;

    if (month === 2) {
      if (day <= 21) {
        return (day + 7);
      }
      else {
        num = 28 - day;
        temp = 7 - num;
        return temp;
      }
    }
    else if (month === 1 || month === 3 || month === 5 || month === 7 ||
      month === 8 || month === 10 || month === 12) {
      if (day <= 24) {
        return (day + 7);
      }
      else {
        num = 31 - day;
        temp = 7 - num;
        return temp;
      }
    }
    else {
      if (day <= 23) {
        return (day + 7);
      }
      else {
        num = 30 - day;
        temp = 7 - num;
        return temp;
      }
    }
  }
}
