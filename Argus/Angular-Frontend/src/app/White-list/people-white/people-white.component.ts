import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-people-white',
  templateUrl: './people-white.component.html',
  styleUrls: ['./people-white.component.css']
})
export class PeopleWhiteComponent implements OnInit {
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
    this.appService.setTitle('White List');
    this.reloadData();
  }
}
