import { Component, OnInit } from '@angular/core';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {ActivatedRoute, Router} from '@angular/router';
import {Session} from '../../../assets/js/SessionStorage';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  id: number;
  user: User;
  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router,
              private SpinnerService: NgxSpinnerService, private userService: UserService) { }

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
        if (data[counter].email.toLowerCase() === emailInp.value.toLowerCase()){
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
    this.SpinnerService.show();
    this.userService.updateUser(this.id, this.user)
      .subscribe(data => {
        // console.log(data);
        setTimeout(() => {
          this.SpinnerService.hide();
        }, 500);
        this.gotoList();
      }, error => console.log(error));
  }

  onSubmit() {
    const tf = this.checkIfExists();
    if (tf !== true)
    {
      this.updateUser();
      this.submitted = true;
    }
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }
}
