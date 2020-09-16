// import {Image} from './image';
import {User} from './user';

export class Notification {
  notificationId: number;
  // notificationImg: Image;
  notificationImg: string;
  listed: string;
  message: string;
  onDate: any;
  atTime: any;
  notificationDeleted: any;
  user: User;
}
