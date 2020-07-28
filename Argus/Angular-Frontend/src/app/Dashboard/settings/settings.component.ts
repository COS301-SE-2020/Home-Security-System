import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {

  constructor(private appService: TitleService) {}

  ReadDB(): void {}

  UpdateDB(): void {}

  ngOnInit(): void {
    this.appService.setTitle('Settings');
    this.ReadDB();
  }
}
