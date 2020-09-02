import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import { SessionService } from '../../model/session.service';
import {Session} from '../../../assets/js/SessionStorage';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {NotificationService} from '../../model/notification.service';
import {JsonObject} from '@angular/compiler-cli/ngcc/src/packages/entry_point';

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  title: string;
  sessionS = new Session();
  user: User;
  newObj: JsonObject;
  cnt = 0;

  constructor(private noteService: NotificationService, private sessService: SessionService, private appService: TitleService,
              private userService: UserService) { }

  clearUserSession(){
    this.newObj = this.sessionS.retrieveUserInfo();
    const idU = this.newObj.id;
    this.sessService.deleteSessionById(idU.toString()).subscribe();
    this.sessionS.deleteSession();
  }

  displayProfilePic(){
    const uPic = document.getElementById('profilePic') as HTMLImageElement;
    this.userService.getUserById(this.sessionS.retrieveUserInfo().id).subscribe(
      data => {
        uPic.src = data.profilePhoto;
      });
  }

  ngOnInit(): void {
    // this.appService.getTitle().subscribe(appTitle => title = appTitle);
    let counter = 0;
    let num = 0;
    this.noteService.getNotificationList().subscribe(data => {
      // console.log(data);
      while (data[counter] != null) {
        if (data[counter].notificationDeleted === null) {
          num += 1;
        }
        counter++;
      }

      if (num < 0)
      {
        num = 0;
      }

      this.cnt = num;
    });

    this.displayProfilePic();
    this.countNotifications();
  }

  countNotifications() {
    setTimeout(() => {
      let counter = 0;
      let num = 0;
      this.noteService.getNotificationList().subscribe(data => {
        // console.log(data);
        while (data[counter] != null) {
          if (data[counter].notificationDeleted === null) {
            num += 1;
          }
          counter++;
        }
        num = num - this.cnt;
        document.getElementById('noteCnt').innerHTML = num.toString();
      });
      this.countNotifications();
    }, 1000);
  }

  clearNotifications() {
    document.getElementById('noteCnt').innerHTML = '0';
    this.ngOnInit();
  }
}
