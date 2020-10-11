import { Component, OnInit } from '@angular/core';
import { RecoverPasswordEmail } from '../../../assets/js/RecoverPasswordEmail.js';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import {Observable} from 'rxjs';
import {AuthService} from '../../model/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  mailer = new RecoverPasswordEmail();
  users: Observable<User[]>;
  user: User;

  constructor(private userService: UserService, private authService: AuthService) { }

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
     // const passwReset = document.getElementById('passwordField2') as HTMLInputElement;
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
                } else {
                  alert('Error, the answer to the question is incorrect');
                }
              }
              counter++;
            }
          }
          if ((data[counter] == null) && (sent === false)){
            alert('Error, email does not exist in the database');
          }
        });
  }

  ngOnInit(): void {
  }

}

