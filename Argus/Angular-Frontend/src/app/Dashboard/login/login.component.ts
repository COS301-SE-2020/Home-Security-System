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

    // Needs to be changed to list through ALL users
    this.userService.getUserById(1)
      .subscribe(
        data => {
          if ((data.email === emailVar.value) && (data.userPass === passVar.value)){
            this.sessionS.createSession(emailVar.value, passVar.value, data.userId);
            alert('Logged in');
            this.sessionS.retrieveUserInfo();
            this.router.navigate(['/dashboard']);
          }
        },
        error => console.log(error));

  }

  ngOnInit(): void {
  }

}
