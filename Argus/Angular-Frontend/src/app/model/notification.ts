import {Image} from './image';

export interface Notification {
  id: number;
  notificationImg: Image;
  message: string;
  onDate: string;
  atTime: string;
  notificationDeleted: string;
}
