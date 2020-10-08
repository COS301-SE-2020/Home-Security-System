import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import { Router } from '@angular/router';
import { TitleService } from '../../title.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { AuthService } from "../../model/auth.service";
import { SessionClass } from "../../model/session";

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  users: Observable<User[]>;
  info: SessionClass = this.authService.retrieveUserInfo();

  id: number;
  user: User;
  temp: string;

  constructor(private authService: AuthService ,private userService: UserService, private appService: TitleService,
              private SpinnerService: NgxSpinnerService, private router: Router) {
  }

  reloadData() {
    this.user = new User();
    this.users = this.userService.getUserList();
  }

  removeUser(id: number) {
    const num = Number(this.info.id);
    if (num === id )
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
              .subscribe(() => {
                setTimeout(() => {
                  this.SpinnerService.hide();
                  this.reloadData();
                }, 500);
              });
          });
    }
  }

  updateUser(id: number) {
    this.user = new User();
    const num = Number(this.info.id);
    this.userService.getUserById(id)
      .subscribe(data => {
        // console.log(data);
        this.user = data;
        this.temp = data.userRole;

        if ( this.user.userRole === 'Admin' && this.info.role === 'Advanced')
        {
          alert('Sorry, you can not edit a user with more privileges than yourself.');
        }
        else if (this.info.role === 'Basic') {
          alert('You are unfortunately not able to edit a user on this page.');
        }
        else {
          if (num === id )
          {
            alert('Sorry, you can not edit yourself from user list.');
          }
          else {
            this.router.navigate(['edit-user', id]);
          }
        }
      });
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
