import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {User} from '../../model/user';
import Session from '../../../assets/js/SessionStorage';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-people-black',
  templateUrl: './people-black.component.html',
  styleUrls: ['./people-black.component.css']
})
export class PeopleBlackComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  person: Observable<Person[]>;
  psn: Person;

  constructor(private personService: PersonService, private SpinnerService: NgxSpinnerService,
              private appService: TitleService, private router: Router) { }

  reloadData() {
    this.psn = new Person();
    this.person = this.personService.getPersonList();
  }

  removePerson(id: number) {
    this.SpinnerService.show();
    this.personService.getPersonById(id)
      .subscribe(
        data => {
          // console.log(data);
          this.psn = data;
          this.psn.personDeleted = new Date();
          this.personService.updatePerson(id, this.psn)
            .subscribe(() => {
              setTimeout(() => {
                this.SpinnerService.hide();
                this.reloadData();
              }, 500);
            });
        });
  }

  updatePerson(id: number){
    this.router.navigate(['edit-person', id]);
  }

  viewPerson(id: number){
    this.router.navigate(['view-person', id]);
  }

  ngOnInit(): void {
    this.appService.setTitle('Person Black-List');
    this.reloadData();
  }

  restorePerson(){
    this.router.navigate(['deleted-threat']);
  }
}
