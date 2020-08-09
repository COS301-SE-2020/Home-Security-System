import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Vehicle} from '../../model/vehicle';
import {VehicleService} from '../../model/vehicle.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-vehicles-white',
  templateUrl: './vehicles-white.component.html',
  styleUrls: ['./vehicles-white.component.css']
})
export class VehiclesWhiteComponent implements OnInit {
  vehicle: Observable<Vehicle[]>;

  constructor(private vehicleService: VehicleService, private appService: TitleService, private router: Router) {
  }

  reloadData() {
    this.vehicle = this.vehicleService.getVehicleList();
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
    this.appService.setTitle('White List');
    this.reloadData();
  }
}
