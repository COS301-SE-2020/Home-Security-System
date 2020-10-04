import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { RecoverPasswordEmail } from '../../../assets/js/RecoverPasswordEmail.js';
import { UserService } from '../../model/user.service';
import { User } from '../../model/user';
import { AuthService } from "../../model/auth.service";

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  mailer = new RecoverPasswordEmail();
  users: Observable<User[]>;

  constructor(private userService: UserService, private authService: AuthService) { }

  sendMail() {
     // const passwReset = document.getElementById('passwordField2') as HTMLInputElement;
     const obj = this.authService.retrieveEmail();

     let counter = 0;
     let sent = false;
     this.userService.getUserList()
      .subscribe(
        data => {
          while (data[counter] != null) {
            if (data[counter].email.toLowerCase() === obj.email.toLowerCase()) {
              this.mailer.sendEmail(obj.email.toLowerCase(), data[counter].userPass);
              sent = true;
              this.authService.logOut();
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

