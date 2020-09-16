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

  sendMail(){
     // const passwReset = document.getElementById('passwordField2') as HTMLInputElement;
     const obj = this.sessionS.retrieveEmail();

     let counter = 0;
     let sent = false;
     this.userService.getUserList()
      .subscribe(
        data => {
          while (data[counter] != null) {
            if (data[counter].email === obj.email) {
              this.mailer.sendEmail(obj.email, data[counter].userPass);
              sent = true;
              this.sessionS.deleteSession();
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

