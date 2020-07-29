export class Image {
  id: number;
  photo: any;
  imageDeleted: any;

  constructor(id: number, img: Image) {
    this.id = id;
    this.photo = img;
  }
}
