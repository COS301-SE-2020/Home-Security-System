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
    const addSurname = document.getElementById('surname') as HTMLDataElement;
    const addUsername = document.getElementById('username') as HTMLDataElement;
    const addEmail = document.getElementById('input') as HTMLDataElement;
    const addUserId = '';
    const addDateDeleted = '';
    let addDateUser;

    function returnUserVal(){
      const isAdmin = document.getElementById('admin') as HTMLInputElement;
      const isAdvanced = document.getElementById('advanced') as HTMLInputElement;
      const isBasic = document.getElementById('basic') as HTMLInputElement;

      if( isAdmin.checked === true) {
        return 'Admin';
      }
      else if( isAdvanced.checked === true) {
        return 'Advanced';
      }
      else if( isBasic.checked === true) {
        return 'Basic';
      }
    }

    const getRole = returnUserVal();

    const addPassword = document.getElementById('pass') as HTMLDataElement;
    const addProfilePicture = '';

    const d = new Date();
    const t = d.getDate() + d.getTime();
    let stringDay = d.getDay() + '/' + d.getMonth() + '/' + d.getFullYear() + ' ';
    stringDay += d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
    addDateUser = stringDay;

    const people = [{
      id: 0,
      name: 'test',
      surname: 'Tester',
      date_created: addDateUser,
      photo: '',
      listedType: 'White'
    }];
    const vehicles = [{
      licence: '123 WER GP',
      licenceType: 'Grey',
      photoOfLicence: '',
      vehicle_id: 0
    }];
    const notificationsUser = [
      {
      at_date_time: addDateUser,
      exp_date_time: '',
      hide: false,
      imageTaken: '',
      message: 'User successfully created',
      notificationType: 'Email'
      }
    ];
    const notSettings = {
      local: true,
      email: true
    };

    const user = {
      u_id: addUserId,
      name : addName.value,
      surname : addSurname.value,
      username: addUsername.value,
      email: addEmail.value,
      role: getRole,
      password: addPassword.value,
      profilePicture: addProfilePicture,
      date_deleted: addDateDeleted,
      person: people,
      vehicle: vehicles,
      notifications: notificationsUser,
      notificationSettings: notSettings
    };
    this.usersList.push(user);
  }



  ngOnInit(): void {
  }

}
