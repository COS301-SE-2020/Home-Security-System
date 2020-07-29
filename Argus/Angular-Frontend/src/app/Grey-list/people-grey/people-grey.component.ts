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

  whiteListPerson(id: number){
    /*return this.personService.addToWhiteList(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));

     */
    this.reloadData();
  }

  blackListPerson(id: number) {
    /*return this.personService.addToBlackList(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
     */
    this.reloadData();
  }

  ngOnInit(): void {
    this.appService.setTitle('Grey List');
    this.reloadData();
  }
}
