import {Image} from './image';

export interface Vehicle {
  id: number;
  vehicleImg: Image;
  vehicleListed: string;
  licenceNo: string;
  vehicleCreated: string;
  vehicleDeleted: string;
}
