import {Image} from './image';

export class Users {
  id: number;
  profilePhoto: Image;
  name: string;
  surname: string;
  email: string;
  username: string;
  userPass: string;
  userRole: string;
  notifyLocal: boolean;
  notifyEmail: boolean;
  userDeleted: string;

  constructor() {
  }
}
