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
        if (data.val().name === 'testAdd') {
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

  UpdateDB(): void {
    let uL = this.db.database.ref('users');
    let obj = null;

    let userName = document.getElementById('firstName') as HTMLDataElement;
    let userSurname = document.getElementById('lastName') as HTMLDataElement;
    let userEmail = document.getElementById('email') as HTMLDataElement;
    let userUsername = document.getElementById('username') as HTMLDataElement;

    uL.orderByValue().on('value', function (snapshot): void {
      snapshot.forEach(function (data): void {
        if (data.val().name === 'testAdd') {
          obj = data.val();
        }
      });
      if (obj === null) {
        console.log('error');
      } else {
        if ( userName.value === obj.name ) {
          console.log('userName did not change' );
        }
        else {
          console.log('userName did change' );
        }

        if ( userSurname.value === obj.surname ) {
          console.log('userSurname did not change' );
        }
        else {
          console.log('userSurname did change' );
        }

        if ( userEmail.value === obj.email ) {
          console.log('userEmail did not change' );
        }
        else {
          console.log('userEmail did change' );
        }

        if ( userUsername.value === obj.username) {
          console.log('userUsername did not change' );
        }
        else {
          console.log('userUsername did change' );
        }
      }
    });
    const newUser = {
      name : userName.value,
      surname : userSurname.value,
      username: userUsername.value,
      email: userEmail.value
    };
    // this.usersList.update(obj, newUser);
    this.usersList.update('realtime-update', newUser);
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

  FillTable(): void{

    let inc = 0;
    const usersL = this.db.database.ref('users');
    usersL.orderByValue().on('value', (snapshot) => {
      snapshot.forEach((data) => {
        const objs = data.val();
        // if (objs.name === 'Johann'){
        if (objs.name != null){
            const table = document.getElementById('valuesTable') as HTMLTableElement;
            const row = table.insertRow();

            const c1 = row.insertCell(0);
            const c2 = row.insertCell(1);
            c1.style.border = '1px solid #dddddd';
            c1.style.background = '#F9F5F4';
            c2.style.border = '1px solid #dddddd';
            c2.style.background = '#F9F5F4';
            c1.innerHTML = objs.name;
            c2.innerHTML = objs.surname;
          }

      });

    });
  }

  /* ======================================================== */

  ngOnInit(): void {
    this.appService.setTitle('User Profile');
    // this.AddDB();
    this.ReadDB();
    // this.FillTable();
  }
}
