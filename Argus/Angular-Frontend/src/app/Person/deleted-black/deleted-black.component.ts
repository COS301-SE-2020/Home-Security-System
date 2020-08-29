import { Component, OnInit } from '@angular/core';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {TitleService} from '../../title.service';
import {User} from '../../model/user';
import {UserService} from '../../model/user.service';
import Session from '../../../assets/js/SessionStorage';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-deleted-black',
  templateUrl: './deleted-black.component.html',
  styleUrls: ['./deleted-black.component.css']
})
export class DeletedBlackComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
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
    this.psn = new Person();
    this.personService.getPersonById(id)
      .subscribe(
        data => {
          // console.log(data);
          this.psn = data;
          this.psn.personDeleted = null;
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
    this.router.navigate(['people-black']);
  }

  deleteOld() {
    let counter = 0;
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth();
    const day = today.getDay();

    this.personService.getPersonList()
      .subscribe(
        data => {
          while (data != null) {
            // console.log(data);
            const date = new Date(data[counter].personDeleted);
            console.log('Date:' + date);
            if (date.getFullYear() === year) {
              if (date.getMonth() === month) {
                const num = date.getDay() + 7;
                if (num === day) {
                  this.personService.deletePerson(data[counter].personId)
                    .subscribe(value => {
                      // console.log(value);
                    }, error => console.log(error));
                }
              }
            }
            counter++;
          }
        }, error => console.log(error));
  }
}
