import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {NotificationService} from '../../model/notification.service';
import {JsonObject} from '@angular/compiler-cli/ngcc/src/packages/entry_point';
import {AuthService} from "../../model/auth.service";
import {SessionClass} from "../../model/session";

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  title: string;
  user: User;
  newObj: JsonObject;
  info: SessionClass = this.authService.retrieveUserInfo()

  constructor(private noteService: NotificationService, public authService:AuthService,
              private appService: TitleService, private userService: UserService) { }

  displayProfilePic() {
      const uPic = document.getElementById('profilePic') as HTMLImageElement;

      let num = Number(this.info.id);
      this.userService.getUserById(num).subscribe(data => { uPic.src = data.profilePhoto; });
  }

  ngOnInit(): void {
      this.displayProfilePic();
  }

  clearNotifications() {
    // document.getElementById('noteCnt').innerHTML = '0';
  }
}
