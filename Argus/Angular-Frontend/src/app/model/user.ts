import {Image} from './image';

export class User {
  userId: number;
  profilePhoto: Image;
  name: string;
  surname: string;
  email: string;
  username: string;
  userPass: string;
  userRole: string;
  notifyLocal: boolean;
  notifyEmail: boolean;
  userDeleted: any;

  /*constructor(id: number, img: Image, fname: string, lname: string, email: string,
              uname: string, pass: string , role: string) {
    this.userId = id;
    this.profilePhoto = img;
    this.name = fname;
    this.surname = lname;
    this.email = email;
    this.username = uname;
    this.userPass = pass;
    this.userRole = role;
  }*/
}