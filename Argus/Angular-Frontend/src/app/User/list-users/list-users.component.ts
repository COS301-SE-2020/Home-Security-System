import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UsersService } from '../../model/users.service';
import { Users } from '../../model/users';
import { Router } from '@angular/router';
import {TitleService} from '../../title.service';


@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  users: Observable<Users[]>;

  constructor(private usersService: UsersService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.users = this.usersService.getAllUsers();
  }

  removeUser(id: number) {
    this.usersService.deleteUser(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  updateUser(id: number){
    this.router.navigate(['edit-user', id]);
  }

  viewUser(id: number){
    this.router.navigate(['view-user', id]);
  }

  ngOnInit(): void {
    this.appService.setTitle('User List');
    this.reloadData();
  }
}
