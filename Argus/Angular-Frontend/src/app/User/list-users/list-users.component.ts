import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import { Router } from '@angular/router';
import {TitleService} from '../../title.service';
import { Session } from '../../../assets/js/SessionStorage.js';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  sessionS = new Session();
  users: Observable<User[]>;
  info: User = this.sessionS.retrieveUserInfo();

  id: number;
  user: User;
  temp: string;

  constructor(private userService: UserService, private appService: TitleService,
              private SpinnerService: NgxSpinnerService, private router: Router) {
  }

  reloadData() {
    this.user = new User();
    this.users = this.userService.getUserList();
  }

  removeUser(id: number) {
    const user = this.sessionS.retrieveUserInfo();
    if ( user.id === id )
    {
      alert('You are unfortunately not able to delete yourself as a user on this page.');
    }
    else {
      this.SpinnerService.show();
      this.userService.getUserById(id)
        .subscribe(
          data => {
            // console.log(data);
            this.user = data;
            this.user.userDeleted = new Date();
            this.userService.updateUser(id, this.user)
              .subscribe(value => {
                // console.log(value);
                setTimeout(() => {
                  this.SpinnerService.hide();
                }, 500);
                this.reloadData();
              }, error => console.log(error));
          }, error => console.log(error));
    }
  }

  updateUser(id: number){
    const userInfo = this.sessionS.retrieveUserInfo();

    this.user = new User();

    this.userService.getUserById(id)
      .subscribe(data => {
        // console.log(data);
        this.user = data;
        this.temp = data.userRole;

        if ( this.user.userRole === 'Admin' && this.info.userRole === 'Advanced')
        {
          alert('Sorry, you can not edit a user with more privileges than yourself.');
        }
        else if ((userInfo.userRole === 'Basic')) {
          alert('You are unfortunately not able to edit a user on this page.');
        }
        else {
          if ( userInfo.id === id )
          {
            alert('Sorry, you can not edit yourself from user list.');
          }
          else {
            this.router.navigate(['edit-user', id]);
          }
        }
      }, error => console.log(error));
  }

  viewUser(id: number) {
    this.router.navigate(['view-user', id]);
  }

  ngOnInit(): void {
    this.appService.setTitle('User List');
    this.reloadData();
  }

  restoreUser() {
    this.router.navigate(['deleted-users']);
  }
}
