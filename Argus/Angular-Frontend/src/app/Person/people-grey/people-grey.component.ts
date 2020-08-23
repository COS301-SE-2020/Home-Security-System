import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import Session from '../../../assets/js/SessionStorage';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-people-grey',
  templateUrl: './people-grey.component.html',
  styleUrls: ['./people-grey.component.css']
})
export class PeopleGreyComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  person: Observable<Person[]>;
  psn: Person;

  constructor(private personService: PersonService, private appService: TitleService,
              private SpinnerService: NgxSpinnerService, private router: Router) {
  }

  reloadData() {
    this.psn = new Person();
    this.person = this.personService.getPersonList();
  }

  addToWhiteList(id: number) {
    this.SpinnerService.show();
    this.personService.getPersonById(id)
      .subscribe(
        data => {
          // console.log(data);
          this.psn = data;
          this.psn.personListed = 'White';
          this.personService.updatePerson(id, this.psn)
            .subscribe(value =>
            {
              // console.log(value);
              setTimeout(() => {
                this.SpinnerService.hide();
              }, 500);
              this.reloadData();
            }, error => console.log(error));
        }, error => console.log(error));
  }

  addToBlackList(id: number) {
    this.SpinnerService.show();
    this.personService.getPersonById(id)
      .subscribe(
        data => {
          // console.log(data);
          this.psn = data;
          this.psn.personListed = 'Black';
          this.personService.updatePerson(id, this.psn)
            .subscribe(value =>
            {
              // console.log(value);
              setTimeout(() => {
                this.SpinnerService.hide();
              }, 500);
              this.reloadData();
            }, error => console.log(error));
        }, error => console.log(error));
  }

  ngOnInit(): void {
    this.appService.setTitle('Grey List');
    this.reloadData();
  }

  // Not tested
  deleteOld() {
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth();
    const day = today.getDay();

    this.personService.getPersonList()
      .subscribe(
        data => {
          // console.log(data);
          const date = new Date(data.personCreated);
          console.log('Date:' + date);
          if (date.getFullYear() === year) {
            if (date.getMonth() === month) {
              const num = date.getDay() + 7;
              if (num === day) {
                this.personService.deletePerson(data.personId)
                  .subscribe(value => {
                    // console.log(value);
                  }, error => console.log(error));
              }
            }
          }
        }, error => console.log(error));
  }
}
