import { Component, OnInit } from '@angular/core';
import {Vehicle} from '../../model/vehicle';
import {ActivatedRoute, Router} from '@angular/router';
import {NgxSpinnerService} from 'ngx-spinner';
import {VehicleService} from '../../model/vehicle.service';

@Component({
  selector: 'app-edit-vehicle',
  templateUrl: './edit-vehicle.component.html',
  styleUrls: ['./edit-vehicle.component.css']
})
export class EditVehicleComponent implements OnInit {
  id: number;
  vehicle: Vehicle;

  constructor(private route: ActivatedRoute, private router: Router,
              private SpinnerService: NgxSpinnerService, private vehicleService: VehicleService) { }

  ngOnInit() {
    this.vehicle = new Vehicle();
    this.id = this.route.snapshot.params.id;

    this.vehicleService.getVehicleById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.vehicle = data;
      });
  }

  updateVehicle() {
    this.SpinnerService.show();
    this.vehicleService.updateVehicle(this.id, this.vehicle)
      .subscribe(() => {
        setTimeout(() => {
          this.SpinnerService.hide();
          this.gotoList();
        }, 500);
      });
  }

  onSubmit() {
    this.updateVehicle();
  }

  gotoList() {
    this.id = this.route.snapshot.params.id;

    this.vehicleService.getVehicleById(this.id)
      .subscribe(data => {
        // console.log(data);
        this.vehicle = data;
        if ( this.vehicle.vehicleListed === 'White')
        {
          this.router.navigate(['/vehicles-cleared']);
        }
        else if ( this.vehicle.vehicleListed === 'Black')
        {
          this.router.navigate(['/vehicles-threat']);
        }
        else {
          this.router.navigate(['/vehicles-unknown']);
        }
      });
  }
}
