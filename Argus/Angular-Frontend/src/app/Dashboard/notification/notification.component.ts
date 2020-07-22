import { Component, OnInit } from '@angular/core';
import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';
import {AppComponent} from '../../app.component';
import {from} from 'rxjs';


class UsersComp {
  constructor(public title, public authors) { }
}
@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css']
})


export class NotificationComponent implements OnInit {
  public usersList: AngularFireList<any>;
  public userDetail;
  public d = new Date();
  public t = this.d.getTime();

  constructor(private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }
  public AddDB(): void {
    const person = [{
      id: 1,
      name: 'Kim',
      surname: 'Possible',
      listedType: 'White',
      photo: '0',
      date_created: '12/03/2020'


  }];
    const notifications = [{
      at_date_time : '16/6/2020 14:12:48',
      exp_date_time : '16/6/2021 14:12:48',
      message : 'Testing the notifications',
      notificationType : 'Local',
      imageTaken : '',
      hide : false
    },
      {
        at_date_time : '16/6/2020 14:14:48',
        exp_date_time : '16/6/2021 14:14:48',
        message : 'Testing the notifications',
        notificationType : 'Email',
        imageTaken : '',
        hide : false
      }
    ];
    const vehicle = [{
      licence: '007MVP-GP',
      listedType: 'White',
      photo: '0',
      vehicle_id: 0
    }];
    const user = {
      name : 'Shaun2',
      surname : 'Vermeulen',
      username: 'ShaunV',
      email: 'sigmacos301@gmail.com',
      role: 'Advanced',
      password: '1234password',
      profilePicture: '',
      notifications: notifications,
      person: person,
      vehicle: vehicle
    };
    this.usersList.push(user);

  }
  ReadDB(): void{
    let uL = this.db.database.ref('users');
    let obj = null;
    uL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        if(data.val().name === 'John'){
          obj = data.val();
        }
      });
      if(obj === null){
        console.log('error');
      }
      else{
        console.log(obj.surname);
      }
    });

  }
  DeleteDB(): void{
    let users = this.db.database.ref('users/-MCNM_KQcI7SQHOg5t4A');
    users.remove()
      .then(function() {
        console.log('Remove succeeded.');
      })
      .catch(function(error) {
        console.log('Remove failed: ' + error.message);
      });
  }

  UpdateDB(): void{
    const adaNameRef = this.db.database.ref('users/-MCNM_KQcI7SQHOg5t4A');
    adaNameRef.update({ name: 'Ada', surname: 'Lovelace' });
  }

  clearTable(): void{
    const table = document.getElementById('notificationsTable') as HTMLTableElement;
    const rows = table.rows.length;
    console.log('len: ' + rows);
    for (let i = 1; i < rows; i++){
      table.deleteRow(1);
    }
  }

  AddNotification(type, messageN): void{
    this.clearTable();
    const usersL = this.db.database.ref('users');
    let uID = '';
    const d = new Date();
    let notificationsArray = [];
    usersL.orderByValue().on('value', (snapshot) => {
      snapshot.forEach((data) => {
        const objs = data.val();
        let inDate = d.getDay() + '/' + d.getMonth() + '/' + d.getFullYear() + ' ' ;
        inDate += + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
        const newNot = {
          at_date_time : inDate,
          exp_date_time : '',
          message : messageN,
          notificationType : type,
          imageTaken : '',
          hide : false
        };
        if (objs.name === 'Shaun') {
          uID = data.key.toString();
          notificationsArray = [];
          notificationsArray = objs.notifications;
          notificationsArray.push(newNot);
        }
      });
    });
    if (uID !== ''){
      this.db.database.ref('users').child(uID).update({notifications: notificationsArray});
    }
  }
  public hideNotification(uid, notNumb): void{
    this.db.database.ref('users').child(uid).child('notifications').child(notNumb).update({hide: true});
  }

  ReadDBNotiications(): void{

    let inc = 0;
    const usersL = this.db.database.ref('users');
    usersL.orderByValue().on('value', (snapshot) => {
      snapshot.forEach((data) => {
        const objs = data.val();
        if (objs.name === 'Shaun'){
          for (let i = 0; i < objs.notifications.length; i++){
            if (!(objs.notifications[i].hide)){
              //console.log(objs.notifications[i].at_date_time);
              //console.log(objs.notifications[i].message);
              const table = document.getElementById('notificationsTable') as HTMLTableElement;
              const row = table.insertRow();

              const btn = document.createElement('button');

              btn.setAttribute('class', 'DeleteButton');
              btn.setAttribute('id', (i + 1).toString());
              inc += 1;
              btn.innerHTML = 'Dismiss';

              btn.onclick = function() {
                let uid = data.key.toString();
                const toDel = btn.getAttribute('id');
                let table = document.getElementById('notificationsTable') as HTMLTableElement;
                table.deleteRow(parseInt(toDel));
                let tot = table.rows.length - 1;

                for (let id = 0; id < table.rows.length; id++) {
                  table.rows[id + 1].cells[3].children[0].setAttribute('id', (id + 1).toString());
                }
              };
              const c1 = row.insertCell(0);
              const c2 = row.insertCell(1);
              const c3 = row.insertCell(2);
              const c4 = row.insertCell(3);
              c1.style.border = '1px solid #dddddd';
              c1.style.background = '#F9F5F4';
              c1.style.color = '#000000';
              c2.style.border = '1px solid #dddddd';
              c2.style.background = '#F9F5F4';
              c2.style.color = '#000000';
              c3.style.border = '1px solid #dddddd';
              c3.style.background = '#F9F5F4';
              c3.style.color = '#000000';
              c4.style.border = '1px solid #dddddd';
              c4.style.background = '#F9F5F4';
              c4.style.color = '#000000';
              c1.innerHTML = objs.notifications[i].at_date_time;
              c2.innerHTML = objs.notifications[i].message;
              c3.innerHTML = objs.notifications[i].notificationType;
              c4.appendChild(btn);
            }
          }
        }
      });

    });
  }


  ngOnInit() {
    this.clearTable();
    this.ReadDBNotiications();
  }

}
