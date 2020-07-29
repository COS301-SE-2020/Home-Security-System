import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';

@Component({
  selector: 'app-people-black',
  templateUrl: './people-black.component.html',
  styleUrls: ['./people-black.component.css']
})
export class PeopleBlackComponent implements OnInit {
  person: Observable<Person[]>;

  constructor(private personService: PersonService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.person = this.personService.getPeopleList();
  }

  removePerson(id: number) {
    this.personService.deletePerson(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  updatePerson(id: number){
    this.router.navigate(['edit-person', id]);
  }

  viewPerson(id: number){
    this.router.navigate(['view-person', id]);
  }

  ngOnInit(): void {
    this.appService.setTitle('Black List');
    this.reloadData();
  }
}
