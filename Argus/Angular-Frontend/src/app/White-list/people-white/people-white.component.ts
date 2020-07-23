import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {AngularFireDatabase} from 'angularfire2/database';

@Component({
  selector: 'app-people-white',
  templateUrl: './people-white.component.html',
  styleUrls: ['./people-white.component.css']
})
export class PeopleWhiteComponent implements OnInit {

  constructor(private appService: TitleService, private db: AngularFireDatabase) {
  }

  ngOnInit(): void {
    this.appService.setTitle('White List');
    this.populateList();
  }

  populateList(): void {
    const usersL = this.db.database.ref('users');
    usersL.orderByValue().on('value', (snapshot) => {
      snapshot.forEach((data) => {
        const objs = data.val();
        if (objs.name === 'Shaun') {
          for (let i = 0; i < objs.person.length; i++) {
            if (objs.person[i].listedType === 'White') {
              const whiteBody = document.getElementById('whiteBody') as HTMLTableElement;
              const row = whiteBody.insertRow();

              const c0 = row.insertCell(0);
              const c1 = row.insertCell(1);
              const c2 = row.insertCell(2);

              c0.innerHTML = objs.person[i].name;

              const image = new Image();
              image.src = objs.person[i].photo;
              image.setAttribute('class', 'listPic');
              c1.appendChild(image);

              const button1 = '<a class="btn btn-primary" data-toggle="modal" data-target="#editModal">Edit</a>';
              const button2 = '<a class="btn btn-primary" [routerLink]="[\'/\']">Delete</a>';
              c2.innerHTML = button1 + button2;
            }
          }
        }
      });
    });
  }
}
