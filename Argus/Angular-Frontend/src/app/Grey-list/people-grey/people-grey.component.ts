import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-people-grey',
  templateUrl: './people-grey.component.html',
  styleUrls: ['./people-grey.component.css']
})
export class PeopleGreyComponent implements OnInit {
  person: Observable<Person[]>;
  arr: Observable<Person[]>;
  psn: Person = new Person();

  constructor(private personService: PersonService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.person = this.personService.getPersonList();
  }

  addToWhiteList(id: number) {
    this.arr = this.personService.getPersonList();

    this.arr.forEach((value) => {
      value.forEach((key) => {
        if (id === key.personId)
        {
          this.psn = key;
          // this.psn.personId = key.personId;
          // this.psn.personImg = key.personImg;
          // this.psn.fname = key.fname;
          // this.psn.lname = key.lname;
          // this.psn.personListed = key.personListed;
          // this.psn.personCreated = key.personCreated;
          // this.psn.personDeleted = key.personDeleted;
        }
      });
    });

    // console.log(this.psn.personListed);
    this.psn.personListed = 'White';

    this.personService.updatePerson(id, this.psn)
      .subscribe(data => console.log(data), error => console.log(error));

    this.reloadData();
  }

  addToBlackList(id: number) {
    this.arr = this.personService.getPersonList();

    this.arr.forEach((value) => {
      value.forEach((key) => {
        if (key.personId === id)
        {
          console.log(key.lname);
          this.psn = key;
          // this.psn.personId = key.personId;
          // this.psn.personImg = key.personImg;
          // this.psn.fname = key.fname;
          // this.psn.lname = key.lname;
          // this.psn.personListed = key.personListed;
          // this.psn.personCreated = key.personCreated;
          // this.psn.personDeleted = key.personDeleted;
        }
      });
    });

    // console.log(this.psn.personListed);
    this.psn.personListed = 'Black';

    this.personService.updatePerson(id, this.psn)
      .subscribe(data => console.log(data), error => console.log(error));

    this.reloadData();
  }

  ngOnInit(): void {
    this.appService.setTitle('Grey List');
    this.reloadData();
  }
}
