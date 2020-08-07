import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {UserService} from '../../model/user.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import { Session } from '../../../assets/js/SessionStorage.js';
import {WebcamImage} from 'ngx-webcam';
import {Observable, Subject} from 'rxjs';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  sessionS = new Session();
  users: Observable<User[]>;
  user: User;
  // user: User = new User();
  submitted = false;

  constructor(private usersService: UserService, private appService: TitleService, private router: Router) {
  }

  // noinspection JSAnnotator
  @ViewChild('video')
  public webcam: ElementRef;

  // noinspection JSAnnotator
  @ViewChild('canvas')
  public canvas: ElementRef;

  public captures: Array<any>;

  public showCam = true;

  public camImg: WebcamImage = null;

  public snapTrigger: Subject<void> = new Subject<void>();

  public trigger_s(): void {
    this.snapTrigger.next();
  }

  public handleShot(img: WebcamImage): void {
    this.camImg = img;
  }

  public toggleCam(): void {
    this.showCam = !this.showCam;
  }

  public get triggerObservable(): Observable<void> {
    return this.snapTrigger.asObservable();
  }

  ngOnInit(): void {
    this.appService.setTitle('Add User');
  }

  newUser(): void {
    this.submitted = false;
    this.user = new User();
  }

  checkIfExists(){
    this.submitted = false;
    let counter = 0;
    const usernameInp = document.getElementById('username') as HTMLInputElement;
    const emailInp = document.getElementById('email') as HTMLInputElement;

    let userObj;
    userObj = this.sessionS.retrieveUserInfo();

    this.usersService.getUserList().subscribe(
      data => {
        if (data[counter].username === usernameInp.value){
          alert('Username is already taken. Please enter another username');
          usernameInp.value = '';
          usernameInp.focus();
        }
        if (data[counter].email === emailInp.value){
          alert('Email address is already in use. Please enter another email address');
          emailInp.value = '';
          emailInp.focus();
        }
        counter++;
      }
    );

  }

  save() {
    const usernameInp = document.getElementById('username') as HTMLInputElement;
    const emailInp = document.getElementById('email') as HTMLInputElement;
    const nameInp = document.getElementById('name') as HTMLInputElement;
    const surnameInp = document.getElementById('surname') as HTMLInputElement;
    const passwordInp = document.getElementById('pass') as HTMLInputElement;

    const adminRole = document.getElementById('admin') as HTMLInputElement;
    const advancedRole = document.getElementById('advanced') as HTMLInputElement;
    const basicRole = document.getElementById('basic') as HTMLInputElement;

    if ((usernameInp.value !== '') && (emailInp.value !== '') && (nameInp.value !== '') && (surnameInp.value !== '') &&
      (passwordInp.value !== '')){
      this.user = new User();

      this.user.name = nameInp.value;
      this.user.surname = surnameInp.value;
      this.user.username = usernameInp.value;
      this.user.userPass = passwordInp.value;

      if (adminRole.checked){
        this.user.userRole = adminRole.value;
      }
      else if (advancedRole.checked){
        this.user.userRole = advancedRole.value;
      }
      else{
        this.user.userRole = basicRole.value;
      }

      this.usersService.addUser(this.user);

      this.submitted = true;
    }
    else{
      this.submitted = false;
      alert('Cannot add a new user. Not all the fields are filled in.');
    }
  }

  onSubmit() {
    this.checkIfExists();
    this.save();
    this.gotoList();
  }

  gotoList() {
    const cardV = document.getElementsByClassName('modal') ;
    // this.router.navigate(['/user-list']);
  }
}
