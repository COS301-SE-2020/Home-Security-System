import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {


  constructor(private appService: TitleService) {}

  /* ======================================================== */

  ReadDB(): void {}

  /* ======================================================== */

  UpdateDB(): void {}

  /* ======================================================== */

  UpdatePic(): void {}

  /* ======================================================== */

  FillTable(): void{}

  /* ======================================================== */

  ngOnInit(): void {
    this.appService.setTitle('User Profile');
    this.ReadDB();
  }
}
