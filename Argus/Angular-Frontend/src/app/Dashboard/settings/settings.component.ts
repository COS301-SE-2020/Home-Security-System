import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import { Session } from '../../../assets/js/SessionStorage.js';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  sessionS = new Session();
  users: Observable<User[]>;
  user: User;

  constructor(private appService: TitleService, private userService: UserService) {}

  enableButton(){
    const buttonEl = document.getElementById('saveBtn') as HTMLButtonElement;
    buttonEl.style.background = '#d4af37';
    buttonEl.disabled = false;
  }

  retrieveSettings(){
    this.user = new User();
    const buttonEl = document.getElementById('saveBtn') as HTMLButtonElement;
    const localSettings = document.getElementById('localSlider') as HTMLInputElement;
    const emailSettings = document.getElementById('emailSlider') as HTMLInputElement;

    buttonEl.style.background = 'grey';
    buttonEl.disabled = true;

    let userObj;
    userObj = this.sessionS.retrieveUserInfo();
    this.userService.getUserById(userObj.id).subscribe(
      data => {
        localSettings.checked = data.notifyLocal;
        emailSettings.checked = data.notifyEmail;
        this.user = data;
      }
    );
  }

  setUserSettings() {
    const localSet = document.getElementById('localSlider') as HTMLInputElement;
    const emailSet = document.getElementById('emailSlider') as HTMLInputElement;
    let userObj;
    userObj = this.sessionS.retrieveUserInfo();
    this.user.notifyEmail = emailSet.checked;
    this.user.notifyLocal = localSet.checked;

    this.userService.updateUser(userObj.id, this.user)
      .subscribe(data => {
        // console.log(data);
        this.retrieveSettings();
        }, error => console.log(error));
  }

  ngOnInit(): void {
    this.appService.setTitle('Settings');
    this.retrieveSettings();
  }
}
