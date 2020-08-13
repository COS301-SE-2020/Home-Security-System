import { Component, OnInit } from '@angular/core';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {ActivatedRoute, Router} from '@angular/router';
import {Session} from '../../../assets/js/SessionStorage';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  id: number;
  user: User;
  submitted: boolean;

  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();

  constructor(private route: ActivatedRoute, private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.user = new User();
    this.id = this.route.snapshot.params.id;

    this.userService.getUserById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.user = data;
      }, error => console.log(error));
  }

  checkIfExists(): boolean {
    this.submitted = false;
    let counter = 0;
    const usernameInp = document.getElementById('username') as HTMLInputElement;
    const emailInp = document.getElementById('email') as HTMLInputElement;

    this.userService.getUserList().subscribe(
      data => {
        if (data[counter].username === usernameInp.value){
          alert('Username is already taken. Please enter another username');
          usernameInp.value = '';
          usernameInp.focus();
          return true;
        }
        if (data[counter].email === emailInp.value){
          alert('Email address is already in use. Please enter another email address');
          emailInp.value = '';
          emailInp.focus();
          return true;
        }
        counter++;
      }
    );

    return false;
  }

  updateUser() {
    this.userService.updateUser(this.id, this.user)
      .subscribe(data => {
        // console.log(data);
      }, error => console.log(error));
    this.gotoList();
  }

  onSubmit() {
    this.updateUser();
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }
}
