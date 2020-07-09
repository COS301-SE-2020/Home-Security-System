import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';

@Component({
  selector: 'app-vehicles-grey',
  templateUrl: './vehicles-grey.component.html',
  styleUrls: ['./vehicles-grey.component.css']
})
export class VehiclesGreyComponent implements OnInit {

  constructor(private appService: TitleService) { }

  ngOnInit(): void {
    this.appService.setTitle('Grey List - Vehicles');
  }

}
