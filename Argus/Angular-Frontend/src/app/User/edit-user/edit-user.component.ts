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
  sessionS = new Session();
  id: number;
  user: User;

  constructor(private route: ActivatedRoute, private router: Router,
              private userService: UserService) { }

  ngOnInit() {
    this.activateButtons();
    this.user = new User();

    this.id = this.route.snapshot.params.id;

    this.userService.getUserById(this.id)
      .subscribe(data => {
        console.log(data);
        this.user = data;
      }, error => console.log(error));
  }

  // --------------------------------------------------------------------

  activateButtons(){
    const editBtn = document.getElementById('editBtn') as HTMLButtonElement;
    const user = this.sessionS.retrieveUserInfo();

    let counter = 0;
    this.userService.getUserList()
      .subscribe(
        data => {

          if ((user.userRole === 'Admin')){
            editBtn.disabled = false;
          }
          else if ((user.userRole === 'Advanced')){
            editBtn.disabled = false;
          }
          else if ((user.userRole === 'Basic')){
            editBtn.disabled = true;
          }
          counter++;
        },
        error => console.log(error));
  }

  // --------------------------------------------------------------------

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
    this.router.navigate(['/user-list']);
  }
}
