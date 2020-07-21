import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';

import {Component, OnInit} from '@angular/core';
import {TitleService} from "../../title.service";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  public usersList: AngularFireList<any>;

  constructor(private appService: TitleService, private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }

  public AddDB(): void {
    const addName = document.getElementById('name') as HTMLDataElement;
    const addSurname = document.getElementById('name') as HTMLDataElement;
    const addUsername = document.getElementById('username') as HTMLDataElement;
    const addEmail = document.getElementById('input') as HTMLDataElement;
    const addRole = 'Unknown';
    const addPassword = document.getElementById('pass') as HTMLDataElement;
    const addProfilePicture = document.getElementById('profilePic') as HTMLDataElement;

    const user = {
      name: addName.value,
      surname: addSurname.value,
      username: addUsername.value,
      email: addEmail.value,
      role: addRole,
      password: addPassword.value,
      profilePicture: atob(addProfilePicture)
    };
    this.usersList.push(user);
  }

  ngOnInit(): void {
    this.appService.setTitle('Add User');
  }

}
