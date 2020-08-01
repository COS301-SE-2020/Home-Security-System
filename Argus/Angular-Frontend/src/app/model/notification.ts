import {Image} from './image';
import {User} from './user';

export class Notification {
  notificationId: number;
  notificationImg: Image;
  message: string;
  onDate: any;
  atTime: any;
  notificationDeleted: any;
  user: User;

  /*constructor(id: number, img: Image, msg: string, u: ArgusUser) {
    this.notificationId = id;
    this.notificationImg = img;
    this.message = msg;
    this.user = u;
  }*/
}
