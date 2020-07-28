import {Image} from './image';

export interface Person {
  id: number;
  personImg: Image;
  fname: string;
  lname: string;
  personListed: string;
  personCreated: string;
  personDeleted: string;
}
