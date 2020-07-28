import {Image} from './image';

export interface Notification {
  id: number;
  notificationImg: Image;
  user_id: number;
  message: string;
  onDate: string;
  atTime: string;
  notificationDeleted: string;
}
