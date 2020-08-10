import { Component, OnInit } from '@angular/core';
import { RecoverPasswordEmail } from '../../../assets/js/RecoverPasswordEmail.js';
import {Session} from '../../../assets/js/SessionStorage';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  sessionS = new Session();
  mailer = new RecoverPasswordEmail();

  constructor() { }

  sendMail(){
     const passwReset = document.getElementById('passwordField2') as HTMLInputElement;
     const obj = this.sessionS.retrieveEmail();
     this.mailer.sendEmail(obj.email, passwReset.value.toString());
     this.sessionS.deleteSession();
  }

  ngOnInit(): void {
  }

}

