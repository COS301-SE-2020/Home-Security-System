import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import { Router } from '@angular/router';
import {TitleService} from '../../title.service';

import { Session } from '../../../assets/js/SessionStorage.js';


@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  sessionS = new Session();
  users: Observable<User[]>;

  constructor(private userService: UserService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.users = this.userService.getUserList();
    this.userService.getUserList()
      .subscribe(
        data => {
          // console.log(data);
        },
        error => console.log(error));
    this.activateButtons();
  }

  removeUser(id: number) {
    const user = this.sessionS.retrieveUserInfo();
    // const deleteBtn = document.getElementById('deleteBtn') as HTMLButtonElement;
    if ((user.userRole === 'Admin')){
      if ( user.id === id )
      {
        // deleteBtn.hidden = true;
        alert('You are unfortunately not able to delete yourself as a user on this page.');
      }
      else {
        this.userService.deleteUser(id)
          .subscribe(
            data => {
              // console.log(data);
            },
            error => console.log(error));
        this.reloadData();
        location.reload();
      }
    }
  }

  updateUser(id: number){
    const user = this.sessionS.retrieveUserInfo();
    if ((user.userRole === 'Basic')) {
      alert('You are unfortunately not able to edit a user on this page.');
    }
    else {
      this.router.navigate(['edit-user', id]);
    }
  }

  viewUser(id: number){
    this.router.navigate(['view-user', id]);
  }

  // ------------------------------------------------------------------

  activateButtons(){
    const addBtn = document.getElementById('addBtn') as HTMLButtonElement;
    const user = this.sessionS.retrieveUserInfo();

    // -----------------------------------------------------
   //  console.log('You are a -> ' + user.userRole);
    // -----------------------------------------------------

    if (user.userRole === 'Basic'){
      addBtn.disabled = true;
      addBtn.hidden = true;
    }
  }

  // ------------------------------------------------------------------

  ngOnInit(): void {
    this.appService.setTitle('User List');
    this.sessionS.retrieveUserInfo();
    this.reloadData();
  }
}
