import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { SessionService } from '../../model/session.service';
import { User } from '../../model/user';
import { SessionClass } from '../../model/session';
import { Session } from '../../../assets/js/SessionStorage.js';
import { RecoverPasswordEmail } from '../../../assets/js/RecoverPasswordEmail.js';
// DO NOT REMOVE THIS
import {forEachComment} from 'tslint';
import {count} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  mailer = new RecoverPasswordEmail();
  sessionS = new Session();
  users: Observable<User[]>;
  sessClass = new SessionClass();

  constructor(private userService: UserService, private sessService: SessionService, private router: Router) { }

  recoverPasswordEmail(){
    const emailInp = document.getElementById('emailIn') as HTMLInputElement;

    if (emailInp.value.toLowerCase() !== '' && emailInp.value.toLowerCase() !== 'E-mail'){
      this.sessionS.recoverySess(emailInp.value.toString());
      this.router.navigate(['/reset-password']);
    }
    else{
      alert('Error, please enter an email address');
    }
  }

  enterLogin(){
    const passInp = document.getElementById('passwordField') as HTMLInputElement;
    // tslint:disable-next-line:only-arrow-functions
    passInp.addEventListener('keyup', function(event) {
      if (event.keyCode === 13) {
        event.preventDefault();
        document.getElementById('loginBtn').click();
      }
    });
  }

  makeSession() {
    const emailVar = document.getElementById('emailInput') as HTMLInputElement;
    const passVar = document.getElementById('passwordField') as HTMLInputElement;

    let counter = 0;
    this.userService.getUserList()
      .subscribe(
        data => {
          while (data[counter] != null) {
            if (data[counter].userDeleted === null) {
              if (((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) || (data[counter].username === emailVar.value))
                && (data[counter].userPass === passVar.value)) {

                // tslint:disable-next-line:max-line-length
                this.sessionS.createSession(data[counter].email, passVar.value, data[counter].userId, data[counter].userRole, data[counter].contactNo);
                this.sessClass.id = data[counter].userId.toString();
                this.sessClass.email = data[counter].email.toString();
                this.sessClass.password = data[counter].userPass.toString();
                this.sessClass.cellphone = data[counter].contactNo.toString();
                this.sessClass.role = data[counter].userRole.toString();

                this.sessService.addToSession(this.sessClass).subscribe();
                this.sessionS.retrieveUserInfo();
                this.router.navigate(['/dashboard']);
              }
              if (((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) || (data[counter].username === emailVar.value)) &&
                (data[counter].userPass !== passVar.value)) {
                alert('The password you entered seems to be incorrect, please retry entering your password.');
                passVar.value = '';
              }
            }
            counter++;
          }
        });
  }

  ngOnInit(): void {
    this.enterLogin();
  }
}
