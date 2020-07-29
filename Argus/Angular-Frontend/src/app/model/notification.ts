import {Image} from './image';
import {Users} from './users';

export class Notification {
  id: number;
  notificationImg: Image;
  message: string;
  onDate: any;
  atTime: any;
  notificationDeleted: any;
  user: Users;

  constructor(id: number, img: Image, msg: string, u: Users) {
    this.id = id;
    this.notificationImg = img;
    this.message = msg;
    this.user = u;
  }
}
