import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {AuthService} from '../../model/auth.service';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {JwtRequest} from '../../model/jwt-request';
import {RecoverPasswordEmail} from '../../../assets/js/RecoverPasswordEmail';
// import {RecoverPasswordEmail} from '../../../assets/js/RecoverPasswordEmail.js';
// import * as bcrypt from 'bcryptjs';

declare function loginSlide(): any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  // mailer = new RecoverPasswordEmail();
  users: Observable<User[]>;
  emailPlaceholder = '';

  mailer = new RecoverPasswordEmail();
  user: User;

  constructor(private userService: UserService, public authService: AuthService,
              private router: Router) {
  }

  hideRecover() {
    document.getElementById('forgotten-container').hidden = true;
  }

  unhide() {
    document.getElementById('forgotten-container').hidden = false;
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
            if (data[counter].email.toLowerCase() === emailInp.value.toLowerCase()) {
              const question = document.getElementById('recoverQuestion') as HTMLElement;
              switch (data[counter].secureQuestion) {
                case 'One':
                  question.innerText = 'What was the name of your first stuffed animal?';
                  break;
                case 'Two':
                  question.innerText = 'Where were you when you had your first kiss?';
                  break;
                case 'Three':
                  question.innerText = 'What street did you live on in third grade?';
                  break;
                case 'Four':
                  question.innerText = 'What was the name of your childhood bully?';
                  break;
                case 'Five':
                  question.innerText = 'What did you want to name your child but never did?';
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
          if (document.getElementById('recoverQuestion').hidden !== false) {
            emailInp.value = '';
            // alert('Error, email does not exist');
            this.createError('Email does not exist, please try again.', 'errorMsgsForgot');

          }
        });

      /*this.router.navigate(['/reset-password']);*/
    } else {
      this.createError('Please enter an email address.', 'errorMsgsForgot');
      // alert('Error, please enter an email address');
    }
  }

  createError(msg, parent) {
    const parentEl = document.getElementById(parent);
    const error = document.createElement('div');
    error.className = 'alert alert-danger errorMsg';
    error.innerText = msg;

    parentEl.appendChild(error);
  }

  clearErrors(){
    document.getElementById('errorMsgs').innerHTML = '';
    document.getElementById('errorMsgsForgot').innerHTML = '';
  }

  recoverPasswordEmail() {
    const emailInp = document.getElementById('emailInput') as HTMLInputElement;
    const ansInp = document.getElementById('answerInput') as HTMLInputElement;

    if (ansInp.value.toLowerCase() !== '' && ansInp.value.toLowerCase() !== 'Answer') {
      this.authService.recoverySession(emailInp.value.toString(), ansInp.value.toString());
      // this.router.navigate(['/reset-password']);
      this.sendMail();
    } else {
      // alert('Error, please enter an answer to the question');
      this.createError('Please enter an answer to the question.', 'errorMsgsForgot');

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

    if ((emailVar.value !== '') && (passVar.value !== '')) {
      this.userService.getUserList().subscribe(data => {
        while (data[counter] != null) {
          if (data[counter].userDeleted === null) {
            if ((data[counter].email.toLowerCase() === emailVar.value.toLowerCase()) ||
              (data[counter].username === emailVar.value)) {

              const obj = new JwtRequest();
              obj.username = emailVar.value;
              obj.password = passVar.value;

              const uid = data[counter].userId;
              const authority = data[counter].userRole;
              const mail = data[counter].email;
              const netName = data[counter].network.netName;

              this.authService.validatePassword(obj).subscribe(value => {
                if (value.password === passVar.value) {
                  this.authService.createSession(uid, authority, mail, netName);
                  this.authService.retrieveUserInfo();
                  this.router.navigate(['/dashboard']);
                } else {
                  this.createError('The password you entered seems to be incorrect, please enter your password again.', 'errorMsgs');
                  // alert('The password you entered seems to be incorrect, please retry entering your password.');
                  passVar.value = '';
                }
              }, () => {});
            }
          }
          counter += 1;
        }
      });
    }
    else {
      this.createError('Please fill in all fields.', 'errorMsgs');
    }
  }

  searchVal(val, arr): boolean {
    let retVal = false;

    if (arr.length !== 0) {
      // tslint:disable-next-line:prefer-for-of
      for (let x = 0; x < arr.length; x++) {
        if (val === arr[x]){
          retVal = true;
        }
      }
    }
    if (val === 0) {
      retVal = true;
    }

    return retVal;
  }

  generatePass(length) {
    let result = '';
    const characterSet1 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const characterSet2 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@$!%*?&';
    const allCaps = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
    const allLower = 'abcdefghijklmnopqrstuvwxyz';
    const numbers = '0123456789';
    const specialChars = '@$!%*?&';
    const characterSet1Length = characterSet1.length;
    const characterSet2Length = characterSet2.length;
    const taken = [];
    let generatedNum = 0;

    for (let i = 0; i < 4; i++) {
      while (this.searchVal(generatedNum, taken) === true) {
        generatedNum = Math.floor(Math.random() * 9) + 1; // ensures it is never 0
      }
      taken.push(generatedNum);
      generatedNum = 0;
    }

    taken.sort();
    let takenInd = 0;

    for ( let i = 0; i < length; i++ ) {
      if (i === 0) {
        result += characterSet1.charAt(Math.floor(Math.random() * characterSet1Length));
      }
      else if (i === taken[takenInd]) {
        switch (takenInd) {
          case 0:
            result += allCaps.charAt(Math.floor(Math.random() * (allCaps.length)));
            takenInd++;
            break;
          case 1:
            result += allLower.charAt(Math.floor(Math.random() * (allLower.length)));
            takenInd++;
            break;
          case 2:
            result += numbers.charAt(Math.floor(Math.random() * (numbers.length)));
            takenInd++;
            break;
          case 3:
            result += specialChars.charAt(Math.floor(Math.random() * (specialChars.length)));
            break;
        }
      }
      else {
        result += characterSet2.charAt(Math.floor(Math.random() * characterSet2Length));
      }
    }

    return result;
  }

  sendMail() {
    const obj = this.authService.retrieveEmail();

    let counter = 0;
    let sent = false;
    this.user = new User();
    this.userService.getUserList()
      .subscribe(data => {
        while (data[counter] != null) {
          if (data[counter].userDeleted == null) {
            if (data[counter].email.toLowerCase() === obj.email.toLowerCase()) {
              if (obj.answer === data[counter].secureAnswer) {
                /*
                let generator = require('generate-password');
                let newPass = generator.generate({length: 10, numbers: true, symbols: true, strict: true });
                */
                const newPass = this.generatePass(10);

                this.user = data[counter];
                this.user.userPass = newPass;
                this.userService.updateUser(this.user.userId, this.user).subscribe();

                this.mailer.sendEmail(obj.email, data[counter].userPass);
                sent = true;
                this.authService.logOut();
                this.showPop('emailSent');
                this.hideRecover();
                loginSlide();
              } else {
                this.createError('The answer to the question is incorrect.', 'errorMsgsForgot');
                // alert('Error, the answer to the question is incorrect');
              }
            }
            counter++;
          }
        }
        if ((data[counter] == null) && (sent === false)){
          this.createError('Email does not exist.', 'errorMsgsForgot');
          // alert('Error, email does not exist in the database');
        }
      });
  }

  showPop(errorID) {
    document.getElementById(errorID).hidden = false;
  }

  closePop(errorID){
    document.getElementById(errorID).hidden = true;
  }
}
