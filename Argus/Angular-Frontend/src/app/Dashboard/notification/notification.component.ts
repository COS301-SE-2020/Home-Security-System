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
      name : 'Shaun',
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
    /*
    let users = this.db.database.ref('users');
    users.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        console.log('The ' + data.key.toString() + ' values are ' + data.val());
        //values of the object
        let obj = data.val();
        //specific value
        console.log(obj.name);
      });

    });
     */
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
    var adaNameRef = this.db.database.ref('users/-MCNM_KQcI7SQHOg5t4A');
    adaNameRef.update({ name: 'Ada', surname: 'Lovelace' });
  }
  /*
  ReadDBNotiications(): void{

    let inc = 1;
    const usersL = this.db.database.ref('users');
    usersL.orderByValue().on('value', function(snapshot): void {
      snapshot.forEach(function(data): void {
        const objs = data.val();
        for (let i = 0; i < objs.notifications.length; i++){

          console.log(objs.notifications[i].at_date_time);
          console.log(objs.notifications[i].message);
          const table = document.getElementById('notificationsTable');
          const row = table.insertRow();

          var btn = document.createElement('button');

          btn.setAttribute('class', 'DeleteButton');
          btn.setAttribute('id', inc);
          inc += 1;
          btn.innerHTML = "Dismiss";

          btn.onclick = function() {
            var toDel = btn.getAttribute('id');
            var table = document.getElementById('notificationsTable');
            table.deleteRow(toDel);
            tot = table.rows.length - 1;

            for (var i = 1; i < table.rows.length; i++) {
              table.rows[i].cells[3].children[0].setAttribute('id', i);
              //alert(table.rows[i].cells[3].children[0].getAttribute("id"));
            }
          }

          const c1 = row.insertCell(0);
          const c2 = row.insertCell(1);
          const c3 = row.insertCell(2);
          const c4 = row.insertCell(3);
          c1.style.border = "1px solid #dddddd";
          c1.style.background = "#F9F5F4";
          c2.style.border = "1px solid #dddddd";
          c2.style.background = "#F9F5F4";
          c3.style.border = "1px solid #dddddd";
          c3.style.background = "#F9F5F4";
          c4.style.border = "1px solid #dddddd";
          c4.style.background = "#F9F5F4";
          c1.innerHTML = objs.notifications[i].at_date_time;
          c2.innerHTML = objs.notifications[i].message;
          c3.innerHTML = objs.notifications[i].notificationType;
          c4.appendChild(btn);
        }

        //c4.innerHTML = objs.notifications.length;

      });

    });
  }

   */

  ngOnInit() {
  }

}
