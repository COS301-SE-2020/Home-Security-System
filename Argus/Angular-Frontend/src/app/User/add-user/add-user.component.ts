import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';

import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  public usersList: AngularFireList<any>;

  constructor(private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }

  public AddDB(): void {
    const addName =  document.getElementById('name') as HTMLDataElement;
    const addSurname = document.getElementById('name') as HTMLDataElement;
    const addUsername = document.getElementById('username') as HTMLDataElement;
    const addEmail = document.getElementById('input') as HTMLDataElement;
    const addRole = 'Unknown';
    const addPassword = document.getElementById('pass') as HTMLDataElement;
    const addProfilePicture = '';

    const user = {
      name : addName.value,
      surname : addSurname.value,
      username: addUsername.value,
      email: addEmail.value,
      role: addRole,
      password: addPassword.value,
      profilePicture: addProfilePicture
    };
    this.usersList.push(user);
  }

  ngOnInit(): void {
  }

}
