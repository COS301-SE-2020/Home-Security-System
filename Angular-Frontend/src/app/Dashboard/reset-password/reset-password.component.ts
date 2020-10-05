import { Component, OnInit } from '@angular/core';
import { RecoverPasswordEmail } from '../../../assets/js/RecoverPasswordEmail.js';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import {Observable} from 'rxjs';
import {AuthService} from "../../model/auth.service";

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

  generatePass(length) {
    let result = '';
    let characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%&?';
    let charactersLength = characters.length;
    for ( let i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
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
            if (data[counter].email.toLowerCase() === obj.email.toLowerCase()) {
              if(obj.answer == data[counter].secureAnswer) {
                /*
                let generator = require('generate-password');
                let newPass = generator.generate({length: 10, numbers: true, symbols: true, strict: true });
                */
                let newPass = this.generatePass(10);

                this.user = data[counter];
                this.user.userPass = newPass;
                this.userService.updateUser(this.user.userId, this.user).subscribe();

                this.mailer.sendEmail(obj.email, data[counter].userPass);
                sent = true;
                this.authService.logOut();
              }
              else{
                alert('Error, the answer to the question is incorrect');
              }
            }
            counter++;
          }
          if ((data[counter] == null) && (sent === false)){
            alert('Error, email does not exist in the database');
          }
        });
  }

  ngOnInit(): void {
  }

}

