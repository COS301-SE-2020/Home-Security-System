import { Component, OnInit } from '@angular/core';
import {UserService} from '../../model/user.service';
import {User} from '../../model/user';
import {ActivatedRoute, Router} from '@angular/router';
import { Session } from '../../../assets/js/SessionStorage.js';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  // sessionS = new Session();
  id: number;
  user: User;

  constructor(private route: ActivatedRoute, private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.user = new User();

    this.id = this.route.snapshot.params.id;

    this.userService.getUserById(this.id)
      .subscribe( data => {
        this.user = data;
      }, error => console.log(error));
    // this.loadModal();
  }

  /*loadModal() {
    const uName = document.getElementById('name') as HTMLInputElement;
    const uSurname = document.getElementById('surname') as HTMLInputElement;
    const uRole = document.getElementById('role') as HTMLInputElement;

    this.userService.getUserById(this.id).subscribe(
      data => {
        uName.value = data.fname;
        uSurname.value = data.lname;
        uRole.value = data.userRole;
      });
  }*/

  updateUser() {
    /*
    const uName = document.getElementById('name') as HTMLInputElement;
    const uSurname = document.getElementById('surname') as HTMLInputElement;
    const uRole = document.getElementById('role') as HTMLInputElement;

    this.user.name = uName.value;
    this.user.surname = uSurname.value;
    this.user.userRole = uRole.value;
    */

    this.userService.updateUser(this.id, this.user).subscribe(data => console.log(data), error => console.log(error));
    this.user = new User();
    this.gotoList();
  }

  onSubmit() {
    this.updateUser();
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }
}
