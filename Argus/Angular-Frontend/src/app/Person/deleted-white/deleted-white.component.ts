import { Component, OnInit } from '@angular/core';
import {User} from '../../model/user';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {UserService} from '../../model/user.service';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import Session from '../../../assets/js/SessionStorage';
import {Router} from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-deleted-white',
  templateUrl: './deleted-white.component.html',
  styleUrls: ['./deleted-white.component.css']
})
export class DeletedWhiteComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  list: Observable<Person[]>;
  person: Observable<Person[]>;
  psn: Person;

  constructor(private personService: PersonService, private userService: UserService,
              private SpinnerService: NgxSpinnerService, private appService: TitleService, private router: Router) { }

  reloadData() {
    this.person = this.personService.getPersonList();
    // this.activateButtons();
  }

  restorePerson(id: number){
    this.SpinnerService.show();
    this.personService.getPersonById(id)
      .subscribe(
        data => {
          // console.log(data);
          this.psn = data;
          this.psn.personDeleted = '';
          this.personService.updatePerson(id, this.psn)
            .subscribe(value => {
              // console.log(value);
              setTimeout(() => {
                this.SpinnerService.hide();
              }, 500);
              this.reloadData();
            }, error => console.log(error));
        }, error => console.log(error));
  }

  ngOnInit(): void {
    this.appService.setTitle('Deleted People');
    this.reloadData();
  }

  back() {
    this.router.navigate(['people-white']);
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
          const date = new Date(data.personDeleted);
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
