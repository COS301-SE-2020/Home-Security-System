import {Image} from './image';

export class Person {
  id: number;
  personImg: Image;
  fname: string;
  lname: string;
  personListed: string;
  personCreated: any;
  personDeleted: any;

  constructor(id: number, img: Image, name: string, surname: string, listed: string) {
    this.id = id;
    this.personImg = img;
    this.fname = name;
    this.lname = surname;
    this.personListed = listed;
  }
}
