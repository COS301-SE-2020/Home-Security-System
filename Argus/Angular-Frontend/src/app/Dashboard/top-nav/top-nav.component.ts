import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Session} from '../../../assets/js/SessionStorage';


@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {
  title: string;
  sessionS = new Session();

  constructor(private appService: TitleService) {}

  ReadDB(): void {}

  clearUserSession(){
    this.sessionS.deleteSession();
  }

  ngOnInit(): void {
    // this.appService.getTitle().subscribe(appTitle => title = appTitle);
    this.ReadDB();
  }

}
