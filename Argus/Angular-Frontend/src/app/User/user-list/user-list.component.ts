import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import { Router } from '@angular/router';
import {TitleService} from '../../title.service';
import { Session } from '../../../assets/js/SessionStorage.js';

@Component({
  selector: 'app-list-users',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {
  sessionS = new Session();
  users: Observable<User[]>;
  info: User = this.sessionS.retrieveUserInfo();

  id: number;
  user: User;
  temp: string;

  constructor(private userService: UserService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.user = new User();
    this.users = this.userService.getUserList();
  }

  removeUser(id: number) {
    const deleteBtn = document.getElementById('deleteBtn') as HTMLButtonElement;
    if (this.info.userRole === 'Admin'){
      deleteBtn.disabled = false;
      if (this.info.userId === id )
      {
        // deleteBtn.hidden = true;
        alert('You are unfortunately not able to delete yourself as a user on this page.');
      }
      else {
        this.userService.deleteUser(id);
        this.reloadData();
        // location.reload();
      }
    }
    else if (this.info.userRole === 'Advanced'){
      deleteBtn.disabled = true;
      deleteBtn.hidden = true;
      // alert('You are unfortunately not able to delete a user on this page.');
    }
    else if (this.info.userRole === 'Basic'){
      deleteBtn.disabled = true;
      deleteBtn.hidden = true;
      // alert('You are unfortunately not able to delete a user on this page.');
    }
  }

  updateUser(id: number){
    const userInfo = this.sessionS.retrieveUserInfo();

    this.user = new User();

    this.userService.getUserById(id)
      .subscribe(data => {
        console.log(data);
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
          this.router.navigate(['edit-user', id]);
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
