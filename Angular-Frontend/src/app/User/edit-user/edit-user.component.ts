import {Component, OnInit} from '@angular/core';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {ActivatedRoute, Router} from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';
import {AuthService} from '../../model/auth.service';
import {SessionClass} from '../../model/session';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  info: SessionClass = this.authService.retrieveUserInfo();
  sessionId = Number(this.info.id);
  id: number;
  user: User;
  submitted = false;

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService,
              private SpinnerService: NgxSpinnerService, private userService: UserService) {
  }

  ngOnInit() {
    this.user = new User();
    this.id = this.route.snapshot.params.id;

    this.userService.getUserById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.user = data;
      });
  }

  checkIfExists(): boolean {
    let counter = 0;
    this.id = this.route.snapshot.params.id;
    const usernameInp = document.getElementById('username') as HTMLInputElement;
    const emailInp = document.getElementById('email') as HTMLInputElement;

    this.userService.getUserList().subscribe(
      data => {
        while (data[counter] != null) {
          if (data[counter].userDeleted === null && data[counter].userId !== this.id) {
            if (data[counter].username === usernameInp.value) {
              this.createError('Username address is already in use. Please enter another username.', 'errorMsgsForgot');
              // alert('Username is already taken. Please enter another username');
              usernameInp.value = '';
              usernameInp.focus();
              return true;
            }
            if (data[counter].email.toLowerCase() === emailInp.value.toLowerCase()) {
              this.createError('Email address is already in use. Please enter another email address', 'errorMsgsForgot');
              // alert('Email address is already in use. Please enter another email address');
              emailInp.value = '';
              emailInp.focus();
              return true;
            }
          }
          counter++;
        }
      }
    );
    return false;
  }

  updateUser() {
    this.SpinnerService.show();
    this.userService.updateUser(this.id, this.user)
      .subscribe(() => {
        setTimeout(() => {
          this.SpinnerService.hide();
        }, 600);
        this.gotoList();
      });
  }

  onSubmit() {
    // const tf = this.checkIfExists();
    // if (!tf)
    // {
    this.updateUser();
    this.submitted = true;
    // }
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }

  createError(msg, parent) {
    const parentEl = document.getElementById(parent);
    const error = document.createElement('div');
    error.className = 'alert alert-danger errorMsg';
    error.innerText = msg;

    parentEl.appendChild(error);
  }

  clearErrors() {
    document.getElementById('errorMsgs').innerHTML = '';
  }
}
