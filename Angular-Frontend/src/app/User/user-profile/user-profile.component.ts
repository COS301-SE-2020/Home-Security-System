import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {TitleService} from '../../title.service';
import {UserService} from '../../model/user.service';
import {ActivatedRoute, Router} from '@angular/router';
import {User} from '../../model/user';
import {WebcamImage} from 'ngx-webcam';
import {Observable, Subject} from 'rxjs';
import {NgxSpinnerService} from 'ngx-spinner';
import {AuthService} from '../../model/auth.service';
import {SessionClass} from '../../model/session';
import {JwtRequest} from '../../model/jwt-request';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  info: SessionClass = this.authService.retrieveUserInfo();
  id: number;
  user: User;

  oldPassword = '';
  newPassword = '';
  confirmPassword = '';
  picCorrect = false;

  constructor(private route: ActivatedRoute, private router: Router, private authService: AuthService,
              private appService: TitleService, private userService: UserService,
              private SpinnerService: NgxSpinnerService) {
  }

  public get triggerObservable(): Observable<void> {
    return this.snapTrigger.asObservable();
  }

  /* ======================================================== */
  /*         START of Camera for taking profile picture       */
  /* ======================================================== */

  // noinspection JSAnnotator
  @ViewChild('video')
  public webcam: ElementRef;

  // noinspection JSAnnotator
  @ViewChild('canvas')
  public canvas: ElementRef;

  public captures: Array<any>;

  public showCam = false;

  public camImg: WebcamImage = null;

  public snapTrigger: Subject<void> = new Subject<void>();

  ngOnInit(): void {
    // this.populateFields();
    this.appService.setTitle('User Profile');
    this.user = new User();

    const num = Number(this.info.id);
    this.userService.getUserById(num)
      .subscribe(data => {
        this.user = data;
      });
  }

  public trigger_s(): void {
    this.snapTrigger.next();
  }

  public handleShot(img: WebcamImage): void {
    this.camImg = img;
  }

  updateUser() {
    const uName = document.getElementById('uFirstName') as HTMLInputElement;
    const uSurname = document.getElementById('uLastName') as HTMLInputElement;
    const uUsername = document.getElementById('uUsername') as HTMLInputElement;
    const uNumber = document.getElementById('uNumber') as HTMLInputElement;
    const uEmail = document.getElementById('uEmail') as HTMLInputElement;

    this.user.fname = uName.value;
    this.user.lname = uSurname.value;
    this.user.contactNo = uNumber.value;
    this.user.email = uEmail.value;
    this.user.username = uUsername.value;

    const num = Number(this.info.id);
    this.userService.updateUser(num, this.user).subscribe(() => {
      this.gotoList();
    });
  }

  updatePassword() {
    const uPassword1 = document.getElementById('passwordField1') as HTMLInputElement;
    const uPassword2 = document.getElementById('passwordField2') as HTMLInputElement;
    const uPassword3 = document.getElementById('passwordField3') as HTMLInputElement;
    const uQuestion = document.getElementById('uQuestion') as HTMLInputElement;
    const uAnswer = document.getElementById('uAnswer') as HTMLInputElement;

    if ((uPassword1.value !== '') && (uPassword2.value !== '') && (uPassword3.value !== '')) {
      if (uPassword2.value !== uPassword3.value) {
        this.createError('Passwords do not match.', 'errorMsgsPass');
      } else {
        const obj = new JwtRequest();
        obj.username = this.info.email;
        obj.password = uPassword1.value;

        this.authService.validatePassword(obj)
          .subscribe(value => {
            if (value.password === uPassword1.value) {
              if (uPassword2.value === uPassword3.value) {
                this.user.userPass = uPassword2.value;
              }

              if (uAnswer.value !== '') {
                this.user.secureQuestion = uQuestion.value;
                this.user.secureAnswer = uAnswer.value;
              }

              const num = Number(this.info.id);
              this.userService.updateUser(num, this.user).subscribe(() => {
                this.gotoList();
              });
            } else {
              this.createError('The password you entered seems to be incorrect, please retry entering your password.', 'errorMsgsPass');
              uPassword1.value = '';
            }
          }, () => {
          });
      }
    } else {
      this.createError('Please fill in all fields.', 'errorMsgsPass');
    }
  }

  updateUserPic() {
    const photoInp = document.getElementById('submitPhoto').getAttribute('src');
    const num = Number(this.info.id);

    if (photoInp === '/assets/Images/blank.jpg') {
      this.user.profilePhoto = this.getDefaultImage();
    } else {
      this.user.profilePhoto = photoInp;
    }

    this.userService.updateUser(num, this.user)
      .subscribe(() => {
        // this.populateFields();
        this.gotoList();
      });
  }

  addIfNew() {
    let counter = 0;
    const usernameInp = document.getElementById('uUsername') as HTMLInputElement;
    const emailInp = document.getElementById('uEmail') as HTMLInputElement;
    let exists = false;
    let email = false;
    let username = false;

    this.userService.getUserList().subscribe(
      data => {
        while (data[counter] != null) {
          if (data[counter].userId !== this.user.userId) {
            if (data[counter].userDeleted === null) {
              if (data[counter].username === usernameInp.value) {
                exists = true;
                username = true;
                usernameInp.value = '';
                usernameInp.focus();
              }
              if (data[counter].email.toLowerCase() === emailInp.value.toLowerCase()) {
                exists = true;
                email = true;
                emailInp.value = '';
                emailInp.focus();
              }
            }
          }
          counter++;
        }
        if (email && username) {
          this.createError('Email address and username are already in use. Please enter another email address and username.', 'errorMsgs');
          // alert('Email address and username are already in use. Please enter another email address and username.');
        } else if (email) {
          this.createError('Email address is already in use. Please enter another email address.', 'errorMsgs');
          // alert('Email address is already in use. Please enter another email address.');
        } else if (username) {
          this.createError('Username is already in use. Please enter another username.', 'errorMsgs');
          // alert('Username address is already in use. Please enter another username.');
        }
      }, () => {
      },
      () => {
        if (!exists) {
          this.updateUser();
        }
      }
    );
  }

  onSubmit() {
    this.addIfNew();
  }

  gotoList() {
    this.router.navigate(['/user-profile']);
    this.SpinnerService.show();
    setTimeout(() => {
      this.SpinnerService.hide();
      window.location.reload();
    }, 600);
  }

  getDefaultImage(): string {
    return 'data:image/jpeg;base64,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';
  }

  public camOn(): void {
    this.showCam = true;
  }

  public camOff(): void {
    this.showCam = false;
  }

  public allowSubmit(): void {
    if (this.picCorrect === false) {
      this.picCorrect = true;
    }
  }

  createError(msg, parent) {
    const parentEl = document.getElementById(parent);
    const error = document.createElement('div');
    error.className = 'alert alert-danger errorMsg';
    error.innerText = msg;

    parentEl.appendChild(error);
    this.showErrorPop('noUpdate');
  }

  clearErrors() {
    document.getElementById('errorMsgs').innerHTML = '';
  }

  showErrorPop(errorID) {
    document.getElementById(errorID).hidden = false;
  }

  closeErrorPop(errorID) {
    document.getElementById(errorID).hidden = true;
  }
}
