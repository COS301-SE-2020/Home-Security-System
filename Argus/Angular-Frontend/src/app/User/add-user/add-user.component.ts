import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {UsersService} from '../../model/users.service';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {Users} from '../../model/users';
import {WebcamImage, WebcamUtil} from 'ngx-webcam';
import {Observable, Subject} from 'rxjs';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {

  user: Users = new Users();
  submitted = false;

  constructor(private usersService: UsersService, private appService: TitleService, private router: Router) {
  }

  @ViewChild('video')
  public webcam: ElementRef;

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
    console.info('received webcam image', img);
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

  save() {
    this.usersService.addUser(this.user)
      .subscribe(data => console.log(data), error => console.log(error));
    this.user = new Users();
    this.gotoList();
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/user-list']);
  }
}
