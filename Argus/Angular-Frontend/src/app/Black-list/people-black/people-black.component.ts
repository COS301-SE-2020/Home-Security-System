import { Component, OnInit } from '@angular/core';
import {TitleService} from "../../title.service";

@Component({
  selector: 'app-people-black',
  templateUrl: './people-black.component.html',
  styleUrls: ['./people-black.component.css']
})
export class PeopleBlackComponent implements OnInit {

  constructor(private appService: TitleService) { }

  ngOnInit(): void {
    this.appService.setTitle('Black List');
  }
}
