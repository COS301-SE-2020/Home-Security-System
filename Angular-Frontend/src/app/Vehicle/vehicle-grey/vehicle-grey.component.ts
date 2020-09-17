import { Component, OnInit } from '@angular/core';
import {User} from '../../model/user';
import {Observable} from 'rxjs';
import {Vehicle} from '../../model/vehicle';
import {VehicleService} from '../../model/vehicle.service';
import {TitleService} from '../../title.service';
import {NgxSpinnerService} from 'ngx-spinner';
import Session from '../../../assets/js/SessionStorage';

@Component({
  selector: 'app-vehicle-grey',
  templateUrl: './vehicle-grey.component.html',
  styleUrls: ['./vehicle-grey.component.css']
})
export class VehicleGreyComponent implements OnInit {
  sessionS = new Session();
  info: User = this.sessionS.retrieveUserInfo();
  vehicle: Observable<Vehicle[]>;
  v: Vehicle;

  constructor(private vehicleService: VehicleService, private appService: TitleService,
              private SpinnerService: NgxSpinnerService) {
  }

  reloadData() {
    this.v = new Vehicle();
    this.vehicle = this.vehicleService.getVehicleList();
    /*setTimeout(() => {
      this.ngOnInit();
    }, 300000);*/
  }

  addToWhiteList(id: number) {
    this.SpinnerService.show();
    this.vehicleService.getVehicleById(id)
      .subscribe(data => {
          // console.log(data);
          this.v = data;
          this.v.vehicleListed = 'White';
          this.vehicleService.updateVehicle(id, this.v)
            .subscribe(() => {
              setTimeout(() => {
                this.SpinnerService.hide();
                this.reloadData();
              }, 500);
            });
        });
  }

  addToBlackList(id: number) {
    this.SpinnerService.show();
    this.vehicleService.getVehicleById(id)
      .subscribe(data => {
          // console.log(data);
          this.v = data;
          this.v.vehicleListed = 'Black';
          this.vehicleService.updateVehicle(id, this.v)
            .subscribe(() => {
              // console.log(value);
              setTimeout(() => {
                this.SpinnerService.hide();
                this.reloadData();
              }, 500);
            });
        });
  }

  ngOnInit(): void {
    this.appService.setTitle('Vehicle Grey-List');
    //this.deleteOld(1);
    this.reloadData();
  }

  deleteAll() {
    let counter = 0;
    this.SpinnerService.show();
    this.vehicleService.getVehicleList()
      .subscribe(data => {
        while (data[counter] != null) {
          if (data[counter].vehicleListed === 'Grey') {
            this.v = data[counter];
            this.v.vehicleDeleted = new Date();
            this.vehicleService.updateVehicle(data[counter].vehicleId, this.v)
              .subscribe();
          }
          counter++;
        }
        setTimeout(() => {
          this.SpinnerService.hide();
          this.reloadData();
        }, 10000);
      });
  }

  removeCompletely(option: number) {
    let counter = 0;
    const today = new Date();
    const year = today.getFullYear();
    const month = ((today.getMonth() + 1) >= 10) ? (today.getMonth() + 1) : '0' + (today.getMonth() + 1);
    const day = today.getDate();

    this.vehicleService.getVehicleList()
      .subscribe(data => {
          while (data[counter] != null) {
            let num = 0;
            // console.log(data);
            const temp = data[counter].vehicleCreated;
            if (temp != null && data[counter].vehicleListed === 'Grey') {
              const tempYear = temp.substr(0, 4);
              const tempMonth = temp.substr(5, 2);
              const tempDay = temp.substr(8, 2);
              if (option === 1) {
                if (tempYear === year.toString()) {
                  const x = Number(tempMonth) + 1;
                  const y = Number(month) + 1;
                  if (tempMonth === month || x === y) {
                    num = this.getDay(Number(tempMonth), Number(tempDay));
                    if (num === day) {
                      this.vehicleService.deleteVehicle(data[counter].vehicleId).subscribe();
                    }
                  }
                }
              }
              else if (option === 2) {
                if (tempYear === year.toString()) {
                  if (Number(tempMonth) < Number(month)) {
                    if (Number(tempDay) === day && Number(tempDay) < 28) {
                      this.vehicleService.deleteVehicle(data[counter].vehicleId).subscribe();
                    }
                    else if (Number(tempDay) === 28) {
                      this.vehicleService.deleteVehicle(data[counter].vehicleId).subscribe();
                    }
                  }
                }
              }
              else if (option === 3) {
                if (Number(tempYear) < year) {
                  if (Number(tempMonth) === Number(month)) {
                    this.vehicleService.deleteVehicle(data[counter].vehicleId).subscribe();
                  }
                }
              }
            }
            counter++;
          }
        });
  }

  deleteOld(option: number) {
    let counter = 0;
    const today = new Date();
    const year = today.getFullYear();
    const month = ((today.getMonth() + 1) >= 10) ? (today.getMonth() + 1) : '0' + (today.getMonth() + 1);
    const day = today.getDate();

    this.vehicleService.getVehicleList()
      .subscribe(data => {
          while (data[counter] != null) {
            let num = 0;
            // console.log(data);
            const temp = data[counter].vehicleCreated;
            if (temp != null && data[counter].vehicleListed === 'Grey') {
              const tempYear = temp.substr(0, 4);
              const tempMonth = temp.substr(5, 2);
              const tempDay = temp.substr(8, 2);
              this.v = new Vehicle();
              this.v = data[counter];
              this.v.vehicleDeleted = new Date();
              if (option === 1) {
                if (tempYear === year.toString()) {
                  const x = Number(tempMonth) + 1;
                  const y = Number(month) + 1;
                  if (tempMonth === month || x === y) {
                    num = this.getDay(Number(tempMonth), Number(tempDay));
                    if (num === day) {
                      this.vehicleService.updateVehicle(data[counter].vehicleId, this.v).subscribe();
                    }
                  }
                }
              }
              else if (option === 2) {
                if (tempYear === year.toString()) {
                  if (Number(tempMonth) < Number(month)) {
                    if (Number(tempDay) === day && Number(tempDay) < 28) {
                      this.vehicleService.updateVehicle(data[counter].vehicleId, this.v).subscribe();
                    }
                    else if (Number(tempDay) === 28) {
                      this.vehicleService.updateVehicle(data[counter].vehicleId, this.v).subscribe();
                    }
                  }
                }
              }
              else if (option === 3) {
                if (Number(tempYear) < year) {
                  if (Number(tempMonth) === Number(month)) {
                    this.vehicleService.updateVehicle(data[counter].vehicleId, this.v).subscribe();
                  }
                }
              }
            }
            counter++;
          }
        });
  }

  getDay(month: number, day: number): number {
    let num = 0;
    let temp = 0;

    if (month === 2) {
      if (day <= 21) {
        return (day + 7);
      }
      else {
        num = 28 - day;
        temp = 7 - num;
        return temp;
      }
    }
    else if (month === 1 || month === 3 || month === 5 || month === 7 || month === 8 || month === 10 || month === 12) {
      if (day <= 24) {
        return (day + 7);
      }
      else {
        num = 31 - day;
        temp = 7 - num;
        return temp;
      }
    }
    else {
      if (day <= 23) {
        return (day + 7);
      }
      else {
        num = 30 - day;
        temp = 7 - num;
        return temp;
      }
    }
  }
}
