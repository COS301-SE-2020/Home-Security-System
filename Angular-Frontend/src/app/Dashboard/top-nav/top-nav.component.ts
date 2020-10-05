import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {NotificationService} from '../../model/notification.service';
import {JsonObject} from '@angular/compiler-cli/ngcc/src/packages/entry_point';
import {AuthService} from '../../model/auth.service';
import {SessionClass} from '../../model/session';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  title: string;
  user: User;
  newObj: JsonObject;
  info: SessionClass = this.authService.retrieveUserInfo();

  constructor(private SpinnerService: NgxSpinnerService, private noteService: NotificationService, public authService: AuthService,
              private appService: TitleService, private userService: UserService) { }

  displayProfilePic() {
      const uPic = document.getElementById('profilePic') as HTMLImageElement;

      const num = Number(this.info.id);
      this.userService.getUserById(num).subscribe(data => { uPic.src = data.profilePhoto; });
  }

  ngOnInit(): void {
      this.displayProfilePic();
  }

  clearNotifications() {
    // document.getElementById('noteCnt').innerHTML = '0';
  }


  enableButton() {
    const buttonEl = document.getElementById('saveBtn') as HTMLButtonElement;
    buttonEl.style.background = '#d4af37';
    buttonEl.disabled = false;
  }

  retrieveSettings() {
    const buttonEl = document.getElementById('saveBtn') as HTMLButtonElement;
    const smsSettings = document.getElementById('smsSlider') as HTMLInputElement;
    const emailSettings = document.getElementById('emailSlider') as HTMLInputElement;

    // buttonEl.style.background = 'grey';
    buttonEl.disabled = true;

    this.userService.getUserById(this.authService.retrieveUserInfo().id).subscribe(
      data => {
        // console.log(data);
        smsSettings.checked = data.notifySMS;
        emailSettings.checked = data.notifyEmail;
        this.user = data;
      }
    );
  }

  setUserSettings() {
    const smsSet = document.getElementById('smsSlider') as HTMLInputElement;
    const emailSet = document.getElementById('emailSlider') as HTMLInputElement;
    this.user.notifyEmail = emailSet.checked;
    this.user.notifySMS = smsSet.checked;

    this.SpinnerService.show();
    const num = Number(this.authService.retrieveUserInfo().id);
    this.userService.updateUser(num, this.user)
      .subscribe(() => {
        setTimeout(() => {
          this.SpinnerService.hide();
          this.retrieveSettings();
        }, 500);
      });
  }
}
