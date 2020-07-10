import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-vehicles-white',
  templateUrl: './vehicles-white.component.html',
  styleUrls: ['./vehicles-white.component.css']
})
export class VehiclesWhiteComponent implements OnInit {

  constructor(private appService: TitleService) { }

  ngOnInit(): void {
    this.appService.setTitle('White List - Vehicles');
  }

}
