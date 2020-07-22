import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';

import {Component, OnInit} from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {

  public usersList: AngularFireList<any>;

  constructor(private appService: TitleService, private db: AngularFireDatabase) {
    this.usersList = db.list('/users');
  }

  FillTable(): void {

    let rowNum = 2;
    const usersL = this.db.database.ref('users');
    usersL.orderByValue().on('value', (snapshot) => {
      snapshot.forEach((data) => {
        const objs = data.val();
        if (objs.name != null) {
          const table = document.getElementById('userListTable') as HTMLTableElement;
          const tableBody = document.getElementById('userTableBody') as HTMLTableElement;
          const row = tableBody.insertRow();

          const c0 = row.insertCell(0);
          const c1 = row.insertCell(1);
          const c2 = row.insertCell(2);
          const c3 = row.insertCell(3);
          const c4 = row.insertCell(4);

          c0.innerHTML = String(rowNum);
          c1.innerHTML = objs.name + ' ' + objs.surname;
          c2.innerHTML = objs.role;

          const image = new Image();
          image.src = objs.profilePicture
          image.setAttribute('class', 'listPic');
          c3.appendChild(image);

          const button1 = '<a class="btn btn-primary" [routerLink]="[\'/edit-user\']">Edit</a>';
          const button2 =  '<a class="btn btn-primary" [routerLink]="[\'/\']">Delete</a>';
          c4.innerHTML = button1 + button2;

          rowNum++; // Row number increment
        }
      });

    });
  }

  ngOnInit(): void {
    this.appService.setTitle('User List');
    this.FillTable();
  }
}
