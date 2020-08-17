import {Component, OnInit, ViewChild, ElementRef} from '@angular/core';
import {UserService} from '../../model/user.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {User} from '../../model/user';
import {WebcamImage} from 'ngx-webcam';
import {Observable, Subject} from 'rxjs';
import {Image} from '../../model/image';
import {ImageService} from '../../model/image.service';
import {Session} from '../../../assets/js/SessionStorage';
import validate = WebAssembly.validate;
import {Person} from '../../model/person';

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
              private appService: TitleService, private router: Router) {
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
    const getRole = this.returnUserRole();

    if ((usernameInp.value !== '') && (emailInp.value !== '') && (nameInp.value !== '') && (surnameInp.value !== '') &&
      (passwordInp.value !== '')) {

      // tslint:disable-next-line:max-line-length
      const b64 = '';
      this.newImage = new Image();
      this.newImage.photo = b64;
      this.imageService.addImage(this.newImage)
        .subscribe(data => {
          // console.log(data);
        }, error => console.log(error));

      // This part doesn't work as expected!
      this.images = this.imageService.getImageList();
      this.images.forEach((value) => {
        if (value.photo === b64) {
          this.newImage = value;
        }
      });

      /*
      this.newUser = new User();
      this.newUser.profilePhoto = this.newImage;
      this.newUser.fname = nameInp.value;
      this.newUser.lname = surnameInp.value;
      this.newUser.email = emailInp.value;
      this.newUser.username = usernameInp.value;
      this.newUser.userPass = passwordInp.value;
      this.newUser.userRole = getRole;
      this.newUser.notifyEmail = true;
      this.newUser.notifyLocal = true;

      this.usersService.addUser(this.newUser)
        .subscribe(data => {
          // console.log(data);
          this.gotoList();
        }, error => console.log(error));
        */

      this.imageService.getImageById(1)
        .subscribe(data => {
          // console.log(data);
          this.newUser = new User();
          this.newUser.profilePhoto = data;
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
              this.gotoList();
            }, error => console.log(error));
        }, error => console.log(error));
    } else {
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
    // const cardV = document.getElementsByClassName('modal') ;
    this.router.navigate(['/user-list']);
  }

  imgTob64() {
    let imgSrc;
    if (this.useDefaultImg) {
      imgSrc = document.getElementById('profilePicDisplay').getAttribute('src');
    } else {
      imgSrc = document.getElementById('submitPhoto').getAttribute('src');
    }
  }

  toggleDefault() {
    this.useDefaultImg = false;
  }

  //
  // imageSrc;
  // sellersPermitFile: any;
  // DriversLicenseFile: any;
  // InteriorPicFile: any;
  // ExteriorPicFile: any;
  // //base64s
  // sellersPermitString: string;
  // DriversLicenseString: string;
  // InteriorPicString: string;
  // ExteriorPicString: string;
  // //json
  // finalJson = {};
  //
  // currentId: number = 0;
  //
  // public picked(event, field) {
  //   this.currentId = field;
  //   const fileList: FileList = event.target.files;
  //   if (fileList.length > 0) {
  //     const file: File = fileList[0];
  //     if (field == 1) {
  //       this.sellersPermitFile = file;
  //       this.handleInputChange(file); //turn into base64
  //     }
  //     else if (field == 2) {
  //       this.DriversLicenseFile = file;
  //       this.handleInputChange(file); //turn into base64
  //     }
  //     else if (field == 3) {
  //       this.InteriorPicFile = file;
  //       this.handleInputChange(file); //turn into base64
  //     }
  //     else if (field == 4) {
  //       this.ExteriorPicFile = file;
  //       this.handleInputChange(file); //turn into base64
  //
  //     }
  //   }
  //   else {
  //     alert("No file selected");
  //   }
  // }
  //
  // handleInputChange(files) {
  //   var file = files;
  //   var pattern = /image-*/;
  //   var reader = new FileReader();
  //   if (!file.type.match(pattern)) {
  //     alert('invalid format');
  //     return;
  //   }
  //   reader.onloadend = this._handleReaderLoaded.bind(this);
  //   reader.readAsDataURL(file);
  // }
  //
  // _handleReaderLoaded(e) {
  //   const reader = e.target;
  //   var base64result = reader.result.substr(reader.result.indexOf(',') + 1);
  //   //this.imageSrc = base64result;
  //   let id = this.currentId;
  //   switch (id) {
  //     case 1:
  //       this.sellersPermitString = base64result;
  //       break;
  //     case 2:
  //       this.DriversLicenseString = base64result;
  //       break;
  //     case 3:
  //       this.InteriorPicString = base64result;
  //       break;
  //     case 4:
  //       this.ExteriorPicString = base64result;
  //       break
  //   }
  //
  //   this.log();
  // }
}
