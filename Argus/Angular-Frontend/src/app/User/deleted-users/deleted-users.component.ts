import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../../model/user';
import {UserService} from '../../model/user.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {Session} from '../../../assets/js/SessionStorage';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-deleted-users',
  templateUrl: './deleted-users.component.html',
  styleUrls: ['./deleted-users.component.css']
})
export class DeletedUsersComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  users: Observable<User[]>;
  user: User;

  constructor(private userService: UserService, private appService: TitleService,
              private SpinnerService: NgxSpinnerService, private router: Router) {
  }

  reloadData() {
    this.user = new User();
    this.users = this.userService.getUserList();
    // this.activateButtons();
  }

  ngOnInit(): void {
    this.appService.setTitle('Deleted Users');
    this.reloadData();
  }

  activateButtons(){
    const restoreBtn = document.getElementById('addBtn') as HTMLButtonElement;

    this.userService.getUserList()
      .subscribe(
        data => {
          // console.log(data);
          if (this.info.userRole === 'Admin'){
            restoreBtn.disabled = false;
          }
          else if (this.info.userRole === 'Advanced'){
            restoreBtn.disabled = false;
          }
          else if (this.info.userRole === 'Basic'){
            restoreBtn.disabled = true;
            restoreBtn.hidden = true;
          }
        }, error => console.log(error));
  }

  restoreUser(id: number) {
    this.SpinnerService.show();
    this.userService.getUserById(id)
      .subscribe(
        data => {
          console.log(data);
          this.user = data;
          this.user.userDeleted = '';
          this.userService.updateUser(id, this.user)
            .subscribe(value => {
              // console.log(data);
              setTimeout(() => {
                this.SpinnerService.hide();
              }, 500);
              this.reloadData();
            }, error => console.log(error));
        }, error => console.log(error));
  }

  back() {
    this.router.navigate(['user-list']);
  }

  // Not tested
  deleteOld() {
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth();
    const day = today.getDay();

    this.userService.getUserList()
      .subscribe(
        data => {
          // console.log(data);
          const date = new Date(data.userDeleted);
          console.log('Date:' + date);
          if (date.getFullYear() === year) {
            if (date.getMonth() === month) {
              const num = date.getDay() + 7;
              if (num === day) {
                this.userService.deleteUser(data.userId)
                  .subscribe(value => {
                    // console.log(value);
                  }, error => console.log(error));
              }
            }
          }
        }, error => console.log(error));
  }
}
