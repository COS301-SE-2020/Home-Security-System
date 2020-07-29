import { Component, OnInit } from '@angular/core';
import {Observable} from 'rxjs';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import {Vehicle} from '../../model/vehicle';
import {VehicleService} from '../../model/vehicle.service';

@Component({
  selector: 'app-vehicles-black',
  templateUrl: './vehicles-black.component.html',
  styleUrls: ['./vehicles-black.component.css']
})
export class VehiclesBlackComponent implements OnInit {
  vehicle: Observable<Vehicle[]>;

  constructor(private vehicleService: VehicleService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.vehicle = this.vehicleService.getAllVehicles();
  }

  removeVehicle(id: number) {
    this.vehicleService.deleteVehicle(id)
      .subscribe(
        data => {
          console.log(data);
          this.reloadData();
        },
        error => console.log(error));
  }

  updateVehicle(id: number){
    this.router.navigate(['edit-vehicle', id]);
  }

  viewVehicle(id: number){
    this.router.navigate(['view-vehicle', id]);
  }

  ngOnInit(): void {
    this.appService.setTitle('Black List');
    this.reloadData();
  }
}
