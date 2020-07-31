import { Component, OnInit } from '@angular/core';
import {UsersService} from '../../model/users.service';
import {Users} from '../../model/users';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  id: number;
  user: Users;

  constructor(private route: ActivatedRoute, private router: Router,
              private userService: UsersService) { }

  ngOnInit() {
    this.user = new Users();

    this.id = this.route.snapshot.params['userId'];

    this.userService.getUserById(this.id)
      .subscribe(data => {
        console.log(data);
        this.user = data;
      }, error => console.log(error));
  }

  updateUser() {
    this.userService.updateUser(this.id, this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new Users();
    this.gotoList();
  }

  onSubmit() {
    this.updateUser();
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }
}
