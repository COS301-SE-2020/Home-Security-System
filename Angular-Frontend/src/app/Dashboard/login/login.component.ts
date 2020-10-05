import { Component, OnInit}  from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../../model/auth.service';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
// import {RecoverPasswordEmail} from '../../../assets/js/RecoverPasswordEmail.js';
// import * as bcrypt from 'bcryptjs';
// DO NOT REMOVE THIS
import {forEachComment} from 'tslint';
import {count} from 'rxjs/operators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //mailer = new RecoverPasswordEmail();
  users: Observable<User[]>;

  constructor(private userService: UserService, public authService: AuthService,
              private router: Router) { }

  hideRecover() {
    document.getElementById("forgotten-container").hidden = true;
  }

  unhide() {
    document.getElementById("forgotten-container").hidden = false;
  }

  ngOnInit(): void {
    this.enterLogin();
  }

  checkEmailForQuestion() {
    const emailInp = document.getElementById('emailInput') as HTMLInputElement;

    if (emailInp.value.toLowerCase() !== '' && emailInp.value.toLowerCase() !== 'E-mail') {
      let counter = 0;
      this.userService.getUserList()
        .subscribe(data => {
          while (data[counter] != null) {
            if (data[counter].email.toLowerCase() === emailInp.value.toLowerCase()){
              let question = document.getElementById('recoverQuestion') as HTMLElement;
              switch (data[counter].secureQuestion){
                case "One":
                  question.innerText = "What was the name of your first stuffed animal?";
                  break;
                case "Two":
                  question.innerText = "Where were you when you had your first kiss?";
                  break;
                case "Three":
                  question.innerText = "What street did you live on in third grade?";
                  break;
                case "Four":
                  question.innerText = "What was the name of your childhood bully?";
                  break;
                case "Five":
                  question.innerText = "What did you want to name your child but never did?";
                  break;
              }
              document.getElementById('recoverQuestion').hidden = false;
              document.getElementById('questionLabel').hidden = false;
              document.getElementById('answerInput').hidden = false;
              document.getElementById('nextBtn').hidden = false;
              document.getElementById('retrieveQ').hidden = true;
            }
            counter += 1;
          }
          if(document.getElementById('recoverQuestion').hidden !== false){
            emailInp.value = "";
            alert('Error, email does not exist');
          }
        });

      /*this.router.navigate(['/reset-password']);*/
    } else {
      alert('Error, please enter an email address');
    }
  }

  recoverPasswordEmail() {
    const emailInp = document.getElementById('emailInput') as HTMLInputElement;
    const ansInp = document.getElementById('answerInput') as HTMLInputElement;

    if (ansInp.value.toLowerCase() !== '' && ansInp.value.toLowerCase() !== 'Answer') {
      this.authService.recoverySession(emailInp.value.toString(), ansInp.value.toString());
      this.router.navigate(['/reset-password']);
    } else {
      alert('Error, please enter an answer to the question');
    }
  }

  enterLogin() {
    const passInp = document.getElementById('passwordField') as HTMLInputElement;
    // tslint:disable-next-line:only-arrow-functions
    passInp.addEventListener('keyup', function(event) {
      if (event.keyCode === 13) {
        event.preventDefault();
        document.getElementById('loginBtn').click();
      }
    });
  }

  /*makeSession() {
    const emailVar = document.getElementById('emailField') as HTMLInputElement;
    const passVar = document.getElementById('passwordField') as HTMLInputElement;
    // const salt = bcrypt.genSaltSync(10);
    // const pass = bcrypt.hashSync(passVar.value, salt);
    // let counter = 0;

    this.userService.getUserList().subscribe(data => {
          while (data[counter] != null) {
            if (data[counter].userDeleted === null) {
              if ((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) ||
                (data[counter].username === emailVar.value)) {
                   (this.authService.authenticate(emailVar, passVar)
                    .subscribe(value => {
                        //this.authService.saveToken(value.token);
                        //this.authService.saveUsername(value.username);

                        this.invalidLogin = false;
                        this.router.navigate(['/dashboard']);
                      },
                      error => {
                        this.invalidLogin = true;
                      }
                    )
                );
              }
            }
            counter += 1;
          }
        });
  }*/

  makeSession() {
    const emailVar = document.getElementById('emailField') as HTMLInputElement;
    const passVar = document.getElementById('passwordField') as HTMLInputElement;
    // const salt = bcrypt.genSaltSync(10);
    // const pass = bcrypt.hashSync(passVar.value, salt);
    let counter = 0;

    this.userService.getUserList().subscribe(data => {
          while (data[counter] != null) {
            if (data[counter].userDeleted === null) {
              if (((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) || (data[counter].username === emailVar.value))
                && (data[counter].userPass === passVar.value)) { // replace passVar.value with pass

                // tslint:disable-next-line:max-line-length
                this.authService.createSession(data[counter].userId, data[counter].userRole, data[counter].email,data[counter].network.netName);
                this.authService.retrieveUserInfo();
                this.router.navigate(['/dashboard']);
              }
              if (((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) || (data[counter].username === emailVar.value)) &&
                (data[counter].userPass !== passVar.value)) { // replace passVar.value with pass
                alert('The password you entered seems to be incorrect, please retry entering your password.');
                passVar.value = '';
              }
            }
            counter += 1;
          }
        });
  }
}
