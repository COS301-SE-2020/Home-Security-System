import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserService } from '../../model/user.service';
import { SessionService } from '../../model/session.service';
import { User } from '../../model/user';
import { SessionClass } from '../../model/session';
import { Session } from '../../../assets/js/SessionStorage.js';
import { RecoverPasswordEmail } from '../../../assets/js/RecoverPasswordEmail.js';
import {forEachComment} from 'tslint';
import {Router} from '@angular/router';

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
  makeSession(){
    const emailVar = document.getElementById('emailInput') as HTMLInputElement;
    const passVar = document.getElementById('passwordField') as HTMLInputElement;

    let counter = 0;
    this.userService.getUserList()
      .subscribe(
        data => {
          while (data != null) {
            if (((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) || (data[counter].username === emailVar.value))
              && (data[counter].userPass === passVar.value)) {
              this.sessionS.createSession(data[counter].email, passVar.value, data[counter].userId, data[counter].userRole);

              this.sessClass.id = data[counter].userId.toString();
              this.sessClass.email = data[counter].email.toString();
              this.sessClass.password = passVar.value.toString();
              this.sessClass.role = data[counter].userRole.toString();

              this.sessService.addToSession(this.sessClass).subscribe();
              /*this.sessService.addToSession(data[counter].userId.toString(), data[counter].email.toString(),
                passVar.value.toString(), data[counter].userRole.toString()).subscribe();*/
              // alert('You are logged in, welcome to Argus');
              this.sessionS.retrieveUserInfo();
              this.router.navigate(['/dashboard']);
            }
            if (((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) || (data[counter].username === emailVar.value)) &&
              (data[counter].userPass !== passVar.value)) {
              alert('The password you entered seems to be incorrect, please retry entering your password.');
              passVar.value = '';
            }
            counter++;
          }
        },
        error => console.log(error));

    // Needs to be changed to list through ALL users
    /*
    this.userService.getUserById(1)
      .subscribe(
        data => {
          if ((data.email.toLowerCase() === emailVar.value.toLowerCase()) && (data.userPass === passVar.value)){
            this.sessionS.createSession(emailVar.value, passVar.value, data.userId, data.userRole);
            alert('Logged in');
            this.sessionS.retrieveUserInfo();
            this.router.navigate(['/dashboard']);
          }
          if ((data.email.toLowerCase() === emailVar.value.toLowerCase()) && (data.userPass !== passVar.value)){
            alert('Password incorrect');
            passVar.value = '';
          }
        },
        error => console.log(error));
      */
  }

  ngOnInit(): void {
    this.enterLogin();
    this.sessionS.retrieveUserInfo();
  }
}
