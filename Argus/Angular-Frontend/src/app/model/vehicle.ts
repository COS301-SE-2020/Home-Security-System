import {Image} from './image';
import {Person} from './person';

export class Vehicle {
  id: number;
  vehicleImg: Image;
  vehicleListed: string;
  licenceNo: string;
  vehicleCreated: string;
  vehicleDeleted: string;
  person: Person;

  constructor(id: number, img: Image, listed: string, num: string, p: Person) {
    this.id = id;
    this.vehicleImg = img;
    this.vehicleListed = listed;
    this.licenceNo = num;
    this.person = p;
  }

}
