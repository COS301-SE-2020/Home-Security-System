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
      .subscribe( data => {
        this.user = data;
      }, error => console.log(error));
    this.loadModal();
  }

  loadModal() {
    const uName = document.getElementById('name') as HTMLInputElement;
    const uSurname = document.getElementById('surname') as HTMLInputElement;
    const uRole = document.getElementById('role') as HTMLInputElement;

    this.userService.getUserById(this.id).subscribe(
      data => {
        uName.value = data.name;
        uSurname.value = data.surname;
        uRole.value = data.userRole;
      });
  }

  // --------------------------------------------------------------------

  activateButtons(){
    const editBtn = document.getElementById('editBtn') as HTMLButtonElement;
    const user = this.sessionS.retrieveUserInfo();

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
            // alert('You are unfortunately not able to change anything on this page, please return to the previous page.');
            editBtn.disabled = true;
          }
        },
        error => console.log(error));
  }

  // --------------------------------------------------------------------

  updateUser() {
    const uName = document.getElementById('name') as HTMLInputElement;
    const uSurname = document.getElementById('surname') as HTMLInputElement;
    const uRole = document.getElementById('role') as HTMLInputElement;

    this.user.name = uName.value;
    this.user.surname = uSurname.value;
    this.user.userRole = uRole.value;

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
