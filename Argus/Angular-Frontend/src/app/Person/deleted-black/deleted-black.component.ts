import { Component, OnInit } from '@angular/core';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {TitleService} from '../../title.service';
import {User} from '../../model/user';
import {UserService} from '../../model/user.service';
import Session from '../../../assets/js/SessionStorage';
import {Observable} from 'rxjs';
import {Router} from '@angular/router';
import {NgxSpinnerService} from "ngx-spinner";

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

  activateButtons(){
    const restoreBtn = document.getElementById('restoreBtn') as HTMLButtonElement;
    const user = this.sessionS.retrieveUserInfo();

    this.userService.getUserList()
      .subscribe(
        data => {
          // console.log(data);
          if (user.userRole === 'Admin'){
            restoreBtn.disabled = false;
          }
          else if (user.userRole === 'Advanced'){
            restoreBtn.disabled = false;
          }
          else if (user.userRole === 'Basic'){
            restoreBtn.disabled = true;
            restoreBtn.hidden = true;
          }
        }, error => console.log(error));
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

  back(){
    this.router.navigate(['people-black']);
  }
}
