import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Session} from '../../../assets/js/SessionStorage';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';


@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  title: string;
  sessionS = new Session();
  user: User;

  constructor(private appService: TitleService, private userService: UserService) {}

  ReadDB(): void {}

  clearUserSession(){
    this.sessionS.deleteSession();
  }

  ngOnInit(): void {
    // this.appService.getTitle().subscribe(appTitle => title = appTitle);
    const uPic = document.getElementById('profilePic') as HTMLImageElement;
    this.userService.getUserById(this.sessionS.retrieveUserInfo().id).subscribe(
      data => {
        uPic.src = data.profilePhoto.photo;
      });
  }

}
