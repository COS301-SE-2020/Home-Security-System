import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../model/user.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {WebcamImage} from 'ngx-webcam';
import {Observable, Subject} from 'rxjs';
import {Image} from '../../model/image';
import {ImageService} from '../../model/image.service';
import {Session} from '../../../assets/js/SessionStorage';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  images: Observable<Image>;
  newImage: Image;
  newUser: User;
  submitted = false;
  useDefaultImg = true;

  constructor(private usersService: UserService, private imageService: ImageService,
              private appService: TitleService, private router: Router, private SpinnerService: NgxSpinnerService) {
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

  checkIfExists(): boolean {
    this.submitted = false;
    let counter = 0;
    const usernameInp = document.getElementById('username') as HTMLInputElement;
    const emailInp = document.getElementById('email') as HTMLInputElement;

    // This doesnt work
    this.usersService.getUserList().subscribe(
      data => {
        if (data[counter].username === usernameInp.value) {
          alert('Username is already taken. Please enter another username');
          usernameInp.value = '';
          usernameInp.focus();
          return true;
        }
        if (data[counter].email.toLowerCase() === emailInp.value.toLowerCase()) {
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

  returnUserRole(): string {
    const adminRole = document.getElementById('admin') as HTMLInputElement;
    const advancedRole = document.getElementById('advanced') as HTMLInputElement;
    const basicRole = document.getElementById('basic') as HTMLInputElement;

    if (adminRole.checked === true) {
      return 'Admin';
    } else if (advancedRole.checked === true) {
      return 'Advanced';
    } else if (basicRole.checked === true) {
      return 'Basic';
    }
  }

  save() {
    const nameInp = document.getElementById('fname') as HTMLInputElement;
    const surnameInp = document.getElementById('surname') as HTMLInputElement;
    const emailInp = document.getElementById('email') as HTMLInputElement;
    const usernameInp = document.getElementById('username') as HTMLInputElement;
    const passwordInp = document.getElementById('pass') as HTMLInputElement;
    const photoInp = document.getElementById('submitPhoto').getAttribute('src');
    const getRole = this.returnUserRole();

    if ((usernameInp.value !== '') && (emailInp.value !== '') && (nameInp.value !== '') && (surnameInp.value !== '') &&
      (passwordInp.value !== '')) {
      this.newUser = new User();
      this.newUser.profilePhoto = photoInp;
      this.newUser.fname = nameInp.value;
      this.newUser.lname = surnameInp.value;
      this.newUser.email = emailInp.value;
      this.newUser.username = usernameInp.value;
      this.newUser.userPass = passwordInp.value;
      this.newUser.userRole = getRole;
      this.newUser.notifyEmail = true;
      this.newUser.notifyLocal = true;

      this.usersService.addUser(this.newUser)
        .subscribe(value => {
          // console.log(value);
        }, error => console.log(error));

      this.gotoList();
    }
    else {
      this.submitted = false;
      alert('Cannot add a new user. Not all the fields are filled in.');
    }
  }

  onSubmit() {
    const tf = this.checkIfExists();
    if (tf !== true) {
      this.save();
      this.submitted = true;
    }
  }

  gotoList() {
    this.SpinnerService.show();
    setTimeout(() => {
      this.SpinnerService.hide();
    }, 500);
    // location.reload();
    // this.router.navigate(['/user-list']);
  }
}
