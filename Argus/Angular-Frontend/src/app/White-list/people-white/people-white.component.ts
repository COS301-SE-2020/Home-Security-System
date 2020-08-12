import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import Session from '../../../assets/js/SessionStorage';

@Component({
  selector: 'app-people-white',
  templateUrl: './people-white.component.html',
  styleUrls: ['./people-white.component.css']
})
export class PeopleWhiteComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  person: Observable<Person[]>;
  psn: Person;

  constructor(private personService: PersonService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.person = this.personService.getPersonList();
    this.psn = new Person();
  }

  removePerson(id: number) {
    this.personService.getPersonById(id)
      .subscribe(
        data => {
          console.log(data);
          this.psn = data;
          this.psn.personDeleted = new Date();
          this.personService.updatePerson(id, this.psn)
            .subscribe(value => {
              // console.log(value);
            }, error => console.log(error));
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

  restorePerson(){
    this.router.navigate(['deleted-white']);
  }
}
