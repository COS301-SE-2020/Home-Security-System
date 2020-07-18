import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';

import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit
{
  public usersList: AngularFireList<any>;

  constructor(private appService: TitleService, private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }

  /* ======================================================== */


  ReadDB(): void {
    let uL = this.db.database.ref('users');
    let obj = null;
    uL.orderByValue().on('value', function (snapshot): void {
      snapshot.forEach(function (data): void {
        if (data.val().name === 'Johann') {
          obj = data.val();
        }
      });
      if (obj === null) {
        console.log('error');
      } else {
        let userName = document.getElementById('firstName') as HTMLDataElement;
        userName.value = obj.name;

        let userSurname = document.getElementById('lastName') as HTMLDataElement;
        userSurname.value = obj.surname;

        let userEmail = document.getElementById('email') as HTMLDataElement;
        userEmail.value = obj.email;

        let userUsername = document.getElementById('username') as HTMLDataElement;
        userUsername.value = obj.username;



        let userNameDisplay = document.getElementById('firstNameDisplay') as HTMLDataElement;
        userNameDisplay.value = obj.name;

        let userSurnameDisplay = document.getElementById('lastNameDisplay') as HTMLDataElement;
        userSurnameDisplay.value = obj.surname;

        let userEmailDisplay = document.getElementById('emailDisplay') as HTMLDataElement;
        userEmailDisplay.value = obj.email;

        let userUsernameDisplay = document.getElementById('usernameDisplay') as HTMLDataElement;
        userUsernameDisplay.value = obj.username;

        // console.log(userName);
        // console.log(userSurname);
        // console.log(userUsername);
        // console.log(userEmail);

      }
    });
  }

  public AddDB(): void {
    const user = {
      name : 'Johann',
      surname : 'Stadler',
      username: 'Stads',
      email: 'sigmacos301@gmail.com',
      role: 'Advanced',
      password: '1234password',
      profilePicture: ''
    };
    this.usersList.push(user);

  }

  /* ======================================================== */

  ngOnInit(): void {
    this.appService.setTitle('User Profile');
  }
}
