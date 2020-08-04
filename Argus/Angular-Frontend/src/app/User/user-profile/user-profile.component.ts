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
    const userSes = this.sessionS.retrieveUserInfo();

    console.log(userSes);

    const FName = document.getElementById('firstNameDisplay') as HTMLDataElement;
    const SName = document.getElementById('lastNameDisplay') as HTMLDataElement;
    const UName = document.getElementById('usernameDisplay') as HTMLDataElement;
    const email = document.getElementById('emailDisplay') as HTMLDataElement;

    FName.value = userSes.name;
    SName.value = userSes.surname;
    UName.value = userSes.username;
    email.value = userSes.email;
  }

  updateUser() {
    this.userService.updateUser(this.id, this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
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
    this.user = new User();

    this.id = this.route.snapshot.params.id;

    this.userService.getUserById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.user = data;
      }, error => console.log(error));
  }
}
