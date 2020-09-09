import { Component, OnInit } from '@angular/core';
import {User} from '../../model/user';
import {Observable} from 'rxjs';
import {NgxSpinnerService} from 'ngx-spinner';
import {TitleService} from '../../title.service';
import {Router} from '@angular/router';
import Session from '../../../assets/js/SessionStorage';
import {Vehicle} from '../../model/vehicle';
import {VehicleService} from '../../model/vehicle.service';

@Component({
  selector: 'app-vehicle-black',
  templateUrl: './vehicle-black.component.html',
  styleUrls: ['./vehicle-black.component.css']
})
export class VehicleBlackComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  vehicle: Observable<Vehicle[]>;
  v: Vehicle;

  constructor(private vehicleService: VehicleService, private SpinnerService: NgxSpinnerService,
              private appService: TitleService, private router: Router) { }

  reloadData() {
    this.vehicle = this.vehicleService.getVehicleList();
  }

  removeVehicle(id: number) {
    this.SpinnerService.show();
    this.vehicleService.getVehicleById(id)
      .subscribe(
        data => {
          // console.log(data);
          this.v = data;
          this.v.vehicleDeleted = new Date();
          this.vehicleService.updateVehicle(id, this.v)
            .subscribe(() => {
              setTimeout(() => {
                this.SpinnerService.hide();
                this.reloadData();
              }, 500);
            });
        });
  }

  updateVehicle(id: number){
    this.router.navigate(['edit-vehicle', id]);
  }

  viewVehicle(id: number){
    this.router.navigate(['view-vehicle', id]);
  }

  ngOnInit(): void {
    this.v = new Vehicle();
    this.appService.setTitle('Vehicle Black-List');
    this.reloadData();
  }

  restoreVehicle(){
    this.router.navigate(['removed-black']);
  }
}
