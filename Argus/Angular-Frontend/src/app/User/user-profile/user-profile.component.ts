import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';

import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {snapshotChanges} from '@angular/fire/database';

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
    const uL = this.db.database.ref('users');
    let obj = null;
    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if (data.val().uniqueId === '123') {
          obj = data.val();
        }
      });
      if (obj === null) {
        console.log('error');
      } else {
        const userName = document.getElementById('firstName') as HTMLDataElement;
        userName.value = obj.name;

        const userSurname = document.getElementById('lastName') as HTMLDataElement;
        userSurname.value = obj.surname;

        const userEmail = document.getElementById('email') as HTMLDataElement;
        userEmail.value = obj.email;

        const userUsername = document.getElementById('username') as HTMLDataElement;
        userUsername.value = obj.username;



        const userNameDisplay = document.getElementById('firstNameDisplay') as HTMLDataElement;
        userNameDisplay.value = obj.name;

        const userSurnameDisplay = document.getElementById('lastNameDisplay') as HTMLDataElement;
        userSurnameDisplay.value = obj.surname;

        const userEmailDisplay = document.getElementById('emailDisplay') as HTMLDataElement;
        userEmailDisplay.value = obj.email;

        const userUsernameDisplay = document.getElementById('usernameDisplay') as HTMLDataElement;
        userUsernameDisplay.value = obj.username;
      }
    });
  }

  UpdateDB(): void {
    const uL = this.db.database.ref('users');
    let obj = null;

    const userName = document.getElementById('firstName') as HTMLDataElement;
    const userSurname = document.getElementById('lastName') as HTMLDataElement;
    const userEmail = document.getElementById('email') as HTMLDataElement;
    const userUsername = document.getElementById('username') as HTMLDataElement;

    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if (data.val().uniqueId === '123') {
          obj = data.val();
        }
      });
    });
    this.usersList.update( '-MCg3fS2bK-gEEgGC5ZX' , { name: userName.value , surname: userSurname.value , username: userUsername.value , email : userEmail.value } );
  }

  UpdatePic(): void {
    const uL = this.db.database.ref('users');
    let obj = null;

    const profilePic = document.getElementById('userPic') as HTMLDataElement;

    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if (data.val().uniqueId === '123') {
          obj = data.val();
        }
      });
    });
    this.usersList.update( '-MCg3fS2bK-gEEgGC5ZX' , { profilePicture: profilePic.value } );
  }

  public AddDB(): void {
    const user = {
      uniqueId : '123',
      name : 'update',
      surname : 'tester',
      username: 'UD',
      email: 'update@email.com',
      role: 'Basic',
      password: '1234password',
      profilePicture: ''
    };
    this.usersList.push(user);

  }

  /* ======================================================== */

  FillTable(): void{

    const inc = 0;
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
