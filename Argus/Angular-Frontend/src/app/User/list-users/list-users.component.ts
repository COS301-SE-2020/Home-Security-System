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

    let inc = 0;
    const usersL = this.db.database.ref('users');
    usersL.orderByValue().on('value', (snapshot) => {
      snapshot.forEach((data) => {
        const objs = data.val();
        if (objs.name != null) {
          const table = document.getElementById('userListTable') as HTMLTableElement;
          const row = table.insertRow();

          const c1 = row.insertCell(0);
          const c2 = row.insertCell(1);
          const c3 = row.insertCell(2);
          const c4 = row.insertCell(3);
          c1.style.border = '1px solid #dddddd';
          c1.style.background = '#F9F5F4';
          c2.style.border = '1px solid #dddddd';
          c2.style.background = '#F9F5F4';
          c3.style.border = '1px solid #dddddd';
          c3.style.background = '#F9F5F4';
          c4.style.border = '1px solid #dddddd';
          c4.style.background = '#F9F5F4';
          c1.innerHTML = objs.name;
          c2.innerHTML = objs.surname;
          c3.innerHTML = objs.role;
          const image = new Image();
          image.src = objs.profilePicture;
          c4.appendChild(image);
          //c4.appendChild(btn);

        }
      });

    });
  }

  ngOnInit(): void {
    this.appService.setTitle('User List');
    this.FillTable();
  }

}
