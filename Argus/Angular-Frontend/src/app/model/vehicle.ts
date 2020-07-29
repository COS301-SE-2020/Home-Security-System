import {Image} from './image';
import {Person} from "./person";

export interface Vehicle {
  id: number;
  vehicleImg: Image;
  vehicleListed: string;
  licenceNo: string;
  vehicleCreated: string;
  vehicleDeleted: string;
  person: Person;
}
