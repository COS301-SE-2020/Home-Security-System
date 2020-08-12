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

  constructor(private userService: UserService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.users = this.userService.getUserList();
    this.activateButtons();
  }

  removeUser(id: number) {
    const deleteBtn = document.getElementById('deleteBtn') as HTMLButtonElement;
    if (this.info.userRole === 'Admin'){
      deleteBtn.disabled = false;
      if (this.info.userId === id )
      {
        deleteBtn.hidden = true;
        alert('You are unfortunately not able to delete yourself as a user on this page.');
      }
      else {
        this.userService.deleteUser(id);
        this.reloadData();
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

  updateUser(id: number) {
    const editBtn = document.getElementById('editBtn') as HTMLButtonElement;
    if (this.info.userRole === 'Admin') {
      this.router.navigate(['edit-user', id]);
    }
    else if (this.info.userRole === 'Advanced') {
      this.router.navigate(['edit-user', id]);
    }
    else if (this.info.userRole === 'Basic') {
      editBtn.disabled = true;
      editBtn.hidden = true;
      // alert('You are unfortunately not able to edit a user on this page.');
    }
  }

  viewUser(id: number) {
    this.router.navigate(['view-user', id]);
  }

  // ------------------------------------------------------------------

  activateButtons(){
    const restoreBtn = document.getElementById('addBtn') as HTMLButtonElement;

    this.userService.getUserList()
      .subscribe(
        data => {
          // console.log(data);
          if (this.info.userRole === 'Admin'){
            restoreBtn.disabled = false;
          }
          else if (this.info.userRole === 'Advanced'){
            restoreBtn.disabled = false;
          }
          else if (this.info.userRole === 'Basic'){
            restoreBtn.disabled = true;
            restoreBtn.hidden = true;
          }
        }, error => console.log(error));
  }

  // ------------------------------------------------------------------

  ngOnInit(): void {
    this.appService.setTitle('User List');
    // this.sessionS.retrieveUserInfo();
    this.reloadData();
  }

  restoreUser() {
    this.router.navigate(['deleted-users']);
  }
}
