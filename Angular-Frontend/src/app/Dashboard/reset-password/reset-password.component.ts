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
    var result           = '';
    var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    var charactersLength = characters.length;
    for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
    }
    return result;
  }


  sendMail() {
     // const passwReset = document.getElementById('passwordField2') as HTMLInputElement;
     const obj = this.authService.retrieveEmail();

     let counter = 0;
     let sent = false;
     let answer = false;
     let uId;
     this.user = new User();
     this.userService.getUserList()
      .subscribe(
        data => {
          while (data[counter] != null) {
            if (data[counter].email.toLowerCase() === obj.email.toLowerCase()) {

              /*var generator = require('generate-password');
              var newPass = generator.generate({length: 10, numbers: true});*/
              var newPass = this.generatePass(10);

              this.user = data[counter];
              this.user.userPass = newPass;
              this.userService.updateUser(this.user.userId, this.user).subscribe();

              if(obj.answer == data[counter].secureAnswer) {
                this.mailer.sendEmail(obj.email, data[counter].userPass);
                sent = true;
                answer = true;
                this.authService.logOut();
              }
              else{
                alert('Error, the answer to the question is incorrect');
              }
            }
            counter++;
          }
          if ((data[counter] == null) && (sent === false) && (answer === true)){
            alert('Error, email does not exist in the database');
          }
        });
  }

  ngOnInit(): void {
  }

}

