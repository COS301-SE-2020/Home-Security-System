import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-people-grey',
  templateUrl: './people-grey.component.html',
  styleUrls: ['./people-grey.component.css']
})
export class PeopleGreyComponent implements OnInit {

  constructor(private appService: TitleService) { }

  ngOnInit(): void {
    this.appService.setTitle('Grey List');
  }

}
