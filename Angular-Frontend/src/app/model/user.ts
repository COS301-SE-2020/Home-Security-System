import {Network} from "./network";

export class User {
  userId: number;
  profilePhoto: string;
  fname: string;
  lname: string;
  contactNo: string;
  email: string;
  username: string;
  userPass: string;
  userRole: string;
  secureQuestion: string;
  secureAnswer: string;
  notifySMS: boolean;
  notifyEmail: boolean;
  userDeleted: any;
  network: Network;
}
