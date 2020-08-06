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
  psn: Person;

  constructor(private personService: PersonService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.person = this.personService.getPersonList();
  }

  whiteListPerson(id: number) {
    this.person = this.personService.getPersonById(id);
    this.psn = new Person();
    this.psn.personId = id;
    // this.psn.personImg = null;
    this.psn.fname = 'Unknown';
    this.psn.lname = 'Unknown';
    this.psn.personListed = 'White';

    this.personService.updatePerson(id, this.psn)
      .subscribe(data => console.log(data), error => console.log(error));

    this.reloadData();
    location.reload();
  }

  blackListPerson(id: number) {
    this.person = this.personService.getPersonById(id);
    this.psn = new Person();
    this.psn.personId = id;
    // this.psn.personImg = null;
    this.psn.fname = 'Unknown';
    this.psn.lname = 'Unknown';
    this.psn.personListed = 'Black';

    this.personService.updatePerson(id, this.psn)
      .subscribe(data => console.log(data), error => console.log(error));

    this.reloadData();
    location.reload();
  }

  ngOnInit(): void {
    this.appService.setTitle('Grey List');
    this.reloadData();
  }
}
