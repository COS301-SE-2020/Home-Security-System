// import {Image} from './image';

export class User {
  userId: number;
  // profilePhoto: Image;
  profilePhoto: string;
  fname: string;
  lname: string;
  email: string;
  username: string;
  userPass: string;
  userRole: string;
  notifyLocal: boolean;
  notifyEmail: boolean;
  userDeleted: any;
}
