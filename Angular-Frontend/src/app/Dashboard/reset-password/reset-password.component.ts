import { Component, OnInit } from '@angular/core';
import { RecoverPasswordEmail } from '../../../assets/js/RecoverPasswordEmail.js';
import {Session} from '../../../assets/js/SessionStorage';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import {Observable} from 'rxjs';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  sessionS = new Session();
  mailer = new RecoverPasswordEmail();
  users: Observable<User[]>;

  constructor(private userService: UserService) { }

  sendMail() {
     // const passwReset = document.getElementById('passwordField2') as HTMLInputElement;
     const obj = this.sessionS.retrieveEmail();

     let counter = 0;
     let sent = false;
     let answer = false;
     this.userService.getUserList()
      .subscribe(
        data => {
          while (data[counter] != null) {
            if (data[counter].email.toLowerCase() === obj.email.toLowerCase()) {
              if(obj.answer == data[counter].secureAnswer){
                this.mailer.sendEmail(obj.email, data[counter].userPass);
                sent = true;
                answer = true;
                this.sessionS.deleteSession();
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

