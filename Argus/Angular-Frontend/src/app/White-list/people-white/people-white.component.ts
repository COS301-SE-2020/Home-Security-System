import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-people-white',
  templateUrl: './people-white.component.html',
  styleUrls: ['./people-white.component.css']
})
export class PeopleWhiteComponent implements OnInit {

  constructor(private appService: TitleService) { }

  ngOnInit(): void {
    this.appService.setTitle('White List');
  }
}
