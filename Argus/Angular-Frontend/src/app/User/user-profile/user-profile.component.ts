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

    let userObj;
    userObj = this.sessionS.retrieveUserInfo();
    /*this.users = */
    this.userService.getUserById(userObj.id).subscribe(
      data => {
        FName.value = data.name;
        SName.value = data.surname;
        UName.value = data.username;
        email.value = data.email;
        password.value = data.userPass;
        this.user = data;
        // console.log(this.user);
      }
    );
  }

  loadModal() {
    const uName = document.getElementById('uFirstName') as HTMLInputElement;
    const uSurname = document.getElementById('uLastName') as HTMLInputElement;
    const uUsername = document.getElementById('uUsername') as HTMLInputElement;
    const uEmail = document.getElementById('uEmail') as HTMLInputElement;
    const uPassword = document.getElementById('uPassword') as HTMLInputElement;

    let userObj;
    userObj = this.sessionS.retrieveUserInfo();

    this.userService.getUserById(userObj.id).subscribe(
      data => {
        uName.value = data.name;
        uSurname.value = data.surname;
        uEmail.value = data.email;
        uUsername.value = data.username;
        uPassword.value = data.userPass;
      });
  }

  updateUser() {
    const uName = document.getElementById('uFirstName') as HTMLInputElement;
    const uSurname = document.getElementById('uLastName') as HTMLInputElement;
    const uUsername = document.getElementById('uUsername') as HTMLInputElement;
    const uEmail = document.getElementById('uEmail') as HTMLInputElement;
    const uPassword = document.getElementById('uPassword') as HTMLInputElement;

    let userObj;
    userObj = this.sessionS.retrieveUserInfo();

    this.user.name = uName.value;
    this.user.surname = uSurname.value;
    this.user.email = uEmail.value;
    this.user.username = uUsername.value;
    this.user.userPass = uPassword.value;

    this.userService.updateUser(userObj.id, this.user).subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
    this.gotoList();
  }

  onSubmit() {
    this.updateUser();
  }

  gotoList() {
    this.router.navigate(['/user-profile']);
    location.reload();
  }

  /* ======================================================== */

  ngOnInit(): void {
    this.populateFields();
    this.appService.setTitle('User Profile');
    this.user = new User();

    this.id = this.route.snapshot.params.id;

    this.userService.getUserById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.user = data;
      }, error => console.log(error));
  }
}
