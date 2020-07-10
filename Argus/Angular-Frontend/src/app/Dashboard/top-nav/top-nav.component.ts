import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {

  title: String;

  constructor(private appService: TitleService) { }

  ngOnInit(): void {
    this.appService.getTitle().subscribe(appTitle => this.title = appTitle);
  }

}
