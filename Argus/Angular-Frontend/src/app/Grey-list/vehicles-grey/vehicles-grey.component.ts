import { Component, OnInit } from '@angular/core';
import {TitleService} from '../../title.service';
import {Observable} from 'rxjs';
import {Vehicle} from '../../model/vehicle';
import {VehicleService} from '../../model/vehicle.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-vehicles-grey',
  templateUrl: './vehicles-grey.component.html',
  styleUrls: ['./vehicles-grey.component.css']
})
export class VehiclesGreyComponent implements OnInit {
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
    this.appService.setTitle('Grey List');
    this.reloadData();
  }
}
