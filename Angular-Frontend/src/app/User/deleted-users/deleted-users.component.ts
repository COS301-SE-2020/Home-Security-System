import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {User} from '../../model/user';
import {UserService} from '../../model/user.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';
import {AuthService} from "../../model/auth.service";
import {SessionClass} from "../../model/session";

@Component({
  selector: 'app-deleted-users',
  templateUrl: './deleted-users.component.html',
  styleUrls: ['./deleted-users.component.css']
})
export class DeletedUsersComponent implements OnInit {
  info: SessionClass = this.authService.retrieveUserInfo();
  users: Observable<User[]>;
  user: User;

  constructor(private userService: UserService, private appService: TitleService, private authService: AuthService,
              private SpinnerService: NgxSpinnerService, private router: Router) {
  }

  reloadData() {
    this.users = this.userService.getUserList();
  }

  ngOnInit(): void {
    this.user = new User();
    this.appService.setTitle('Deleted Users');
    this.reloadData();
    //this.deleteOld(1);
  }

  restoreUser(id: number) {
    this.SpinnerService.show();
    this.userService.getUserById(id)
      .subscribe(
        data => {
          // console.log(data);
          this.user = data;
          this.user.userDeleted = '';
          this.userService.updateUser(id, this.user)
            .subscribe(() => {
              setTimeout(() => {
                this.SpinnerService.hide();
                this.reloadData();
              }, 600);
            });
        });
  }

  back() {
    this.router.navigate(['user-list']);
  }

  deleteAll() {
    let counter = 0;
    this.SpinnerService.show();
    this.userService.getUserList()
      .subscribe(data => {
        while (data[counter] != null) {
          if (data[counter].userDeleted != null && data[counter].network.netName ==this.info.network) {
            this.userService.deleteUser(data[counter].userId).subscribe();
          }
          counter++;
        }
        setTimeout(() => {
          this.SpinnerService.hide();
          this.reloadData();
        }, 10000);
      });
  }

  deleteOld(option: number) {
    let counter = 0;
    const today = new Date();
    const year = today.getFullYear();
    const month = ((today.getMonth() + 1) >= 10) ? (today.getMonth() + 1) : '0' + (today.getMonth() + 1);
    const day = today.getDate();

    this.userService.getUserList()
      .subscribe(
        data => {
          while (data[counter] != null) {
            let num = 0;
            // console.log(data);
            const temp = data[counter].userDeleted;
            if (temp != null) {
              const tempYear = temp.substr(0, 4);
              const tempMonth = temp.substr(5, 2);
              const tempDay = temp.substr(8, 2);
              if (option === 1) {
                if (tempYear === year.toString()) {
                  const x = Number(tempMonth) + 1;
                  const y = Number(month) + 1;
                  if (tempMonth === month || x === y) {
                    num = this.getDay(Number(tempMonth), Number(tempDay));
                    if (num === day) {
                      this.userService.deleteUser(data[counter].userId).subscribe();
                    }
                  }
                }
              }
              else if (option === 2) {
                if (tempYear === year.toString()) {
                  if (Number(tempMonth) < Number(month)) {
                    if (Number(tempDay) === day && Number(tempDay) < 28) {
                      this.userService.deleteUser(data[counter].userId).subscribe();
                    }
                    else if (Number(tempDay) === 28) {
                      this.userService.deleteUser(data[counter].userId).subscribe();
                    }
                  }
                }
              }
              else if (option === 3) {
                if (Number(tempYear) < year) {
                  if (Number(tempMonth) === Number(month)) {
                    this.userService.deleteUser(data[counter].userId).subscribe();
                  }
                }
              }
            }
            counter++;
          }
        });
  }

  getDay(month: number, day: number): number {
    let num = 0;
    let temp = 0;

    if (month === 2) {
      if (day <= 21) {
        return (day + 7);
      }
      else {
        num = 28 - day;
        temp = 7 - num;
        return temp;
      }
    }
    else if (month === 1 || month === 3 || month === 5 || month === 7 || month === 8 || month === 10 || month === 12) {
      if (day <= 24) {
        return (day + 7);
      }
      else {
        num = 31 - day;
        temp = 7 - num;
        return temp;
      }
    }
    else {
      if (day <= 23) {
        return (day + 7);
      }
      else {
        num = 30 - day;
        temp = 7 - num;
        return temp;
      }
    }
  }
}
