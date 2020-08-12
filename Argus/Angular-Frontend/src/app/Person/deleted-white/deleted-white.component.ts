import { Component, OnInit } from '@angular/core';
import {User} from '../../model/user';
import {Person} from '../../model/person';
import {PersonService} from '../../model/person.service';
import {UserService} from '../../model/user.service';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import Session from '../../../assets/js/SessionStorage';
import {Router} from '@angular/router';

@Component({
  selector: 'app-deleted-white',
  templateUrl: './deleted-white.component.html',
  styleUrls: ['./deleted-white.component.css']
})
export class DeletedWhiteComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  person: Observable<Person[]>;
  psn: Person;

  constructor(private personService: PersonService, private userService: UserService,
              private appService: TitleService, private router: Router) { }

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
    this.personService.getPersonById(id)
      .subscribe(
        data => {
          console.log(data);
          this.psn = data;
          this.psn.personDeleted = '';
          this.personService.updatePerson(id, this.psn)
            .subscribe(value => {
              // console.log(value);
            }, error => console.log(error));
          this.reloadData();
        }, error => console.log(error));
  }

  ngOnInit(): void {
    this.appService.setTitle('Deleted People');
    this.reloadData();
  }

  back(){
    this.router.navigate(['people-white']);
  }
}
