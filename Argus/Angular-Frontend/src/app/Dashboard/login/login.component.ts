import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import { Session } from '../../../assets/js/SessionStorage.js';
import {forEachComment} from 'tslint';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  sessionS = new Session();
  users: Observable<User[]>;

  constructor(private userService: UserService, private router: Router) { }

  makeSession(){
    const emailVar = document.getElementById('emailInput') as HTMLInputElement;
    const passVar = document.getElementById('passwordField') as HTMLInputElement;


    let counter = 0;
    this.userService.getUserList()
      .subscribe(
        data => {
          if ((data[counter].email === emailVar.value) && (data[counter].userPass === passVar.value)){
            this.sessionS.createSession(emailVar.value, passVar.value, data[counter].userId, data[counter].userRole);
            alert('Logged in');
            this.sessionS.retrieveUserInfo();
            this.router.navigate(['/dashboard']);
          }
          if ((data[counter].email === emailVar.value) && (data[counter].userPass !== passVar.value)){
            alert('Password incorrect');
            passVar.value = '';
          }
          counter++;
        },
        error => console.log(error));

    // Needs to be changed to list through ALL users
    /*
    this.userService.getUserById(1)
      .subscribe(
        data => {
          if ((data.email === emailVar.value) && (data.userPass === passVar.value)){
            this.sessionS.createSession(emailVar.value, passVar.value, data.userId, data.userRole);
            alert('Logged in');
            this.sessionS.retrieveUserInfo();
            this.router.navigate(['/dashboard']);
          }
          if ((data.email === emailVar.value) && (data.userPass !== passVar.value)){
            alert('Password incorrect');
            passVar.value = '';
          }
        },
        error => console.log(error));
      */
  }

  ngOnInit(): void {
    this.sessionS.retrieveUserInfo();
  }

}
