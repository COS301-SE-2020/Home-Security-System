import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {UserService} from '../../model/user.service';
import { Session } from '../../../assets/js/SessionStorage.js';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../model/user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  sessionS = new Session();
  userObj: Session = this.sessionS.retrieveUserInfo();
  id: number;
  user: User;

  constructor(private route: ActivatedRoute, private router: Router, private appService: TitleService, private userService: UserService) {}

  /* ======================================================== */

  populateFields(){
    const FName = document.getElementById('firstNameDisplay') as HTMLDataElement;
    const SName = document.getElementById('lastNameDisplay') as HTMLDataElement;
    const UName = document.getElementById('usernameDisplay') as HTMLDataElement;
    const email = document.getElementById('emailDisplay') as HTMLDataElement;
    const password = document.getElementById('passwordDisplay') as HTMLDataElement;

    this.userService.getUserById(this.userObj.id)
      .subscribe(data => {
        FName.value = data.fname;
        SName.value = data.lname;
        UName.value = data.username;
        email.value = data.email;
        password.value = data.userPass;
        this.user = data;
      }, error => console.log(error));
  }

  loadModal() {
    const uName = document.getElementById('uFirstName') as HTMLInputElement;
    const uSurname = document.getElementById('uLastName') as HTMLInputElement;
    const uUsername = document.getElementById('uUsername') as HTMLInputElement;
    const uEmail = document.getElementById('uEmail') as HTMLInputElement;
    const uPassword = document.getElementById('uPassword') as HTMLInputElement;

    this.userService.getUserById(this.userObj.id)
      .subscribe(data => {
        uName.value = data.fname;
        uSurname.value = data.lname;
        uEmail.value = data.email;
        uUsername.value = data.username;
        uPassword.value = data.userPass;
      }, error => console.log(error));
  }

  updateUser() {
    const uName = document.getElementById('uFirstName') as HTMLInputElement;
    const uSurname = document.getElementById('uLastName') as HTMLInputElement;
    const uUsername = document.getElementById('uUsername') as HTMLInputElement;
    const uEmail = document.getElementById('uEmail') as HTMLInputElement;
    const uPassword = document.getElementById('uPassword') as HTMLInputElement;

    this.user.fname = uName.value;
    this.user.lname = uSurname.value;
    this.user.email = uEmail.value;
    this.user.username = uUsername.value;
    this.user.userPass = uPassword.value;

    this.userService.updateUser(this.userObj.id, this.user)
      .subscribe(data => {
        // console.log(data);
        this.populateFields();
      }, error => console.log(error));
    this.gotoList();
  }

  onSubmit() {
    this.updateUser();
  }

  gotoList() {
    this.router.navigate(['/user-profile']);
  }

  /* ======================================================== */

  ngOnInit(): void {
    this.populateFields();
    this.appService.setTitle('User Profile');
  }
}
