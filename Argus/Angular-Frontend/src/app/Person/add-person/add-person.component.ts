import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import { PersonService } from '../../model/person.service';
import { Person } from '../../model/person';
import { Image } from '../../model/image';
import { Router } from '@angular/router';
import {TitleService} from '../../title.service';
import {WebcamImage} from 'ngx-webcam';
import {ImageService} from '../../model/image.service';
import {NgxSpinnerService} from 'ngx-spinner';

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent implements OnInit {
  images: Observable<Image>;
  newPerson: Person;
  newImage: Image;
  submitted = false;

  constructor(private personService: PersonService, private imageService: ImageService,
              private appService: TitleService, private router: Router,  private SpinnerService: NgxSpinnerService) {
  }

  // noinspection JSAnnotator
  @ViewChild('video')
  public webcam: ElementRef;

  // noinspection JSAnnotator
  @ViewChild('canvas')
  public canvas: ElementRef;

  public captures: Array<any>;

  public showCam = true;

  public camImg: WebcamImage = null;

  public snapTrigger: Subject<void> = new Subject<void>();

  public trigger_s(): void {
    this.snapTrigger.next();
  }

  public handleShot(img: WebcamImage): void {
    this.camImg = img;
  }

  public toggleCam(): void {
    this.showCam = !this.showCam;
  }

  public get triggerObservable(): Observable<void> {
    return this.snapTrigger.asObservable();
  }

  ngOnInit(): void {
    this.appService.setTitle('Add Person');
  }

  returnPersonListed(): string{
    const isWhite = document.getElementById('whiteList') as HTMLInputElement;
    const isBlack = document.getElementById('blackList') as HTMLInputElement;

    if (isWhite.checked === true) {
      return 'White';
    } else if (isBlack.checked === true) {
      return 'Black';
    }
  }

  addPerson(): void{
    const addName = document.getElementById('fname') as HTMLInputElement;
    const addSurname = document.getElementById('lname') as HTMLInputElement;
    const photoInp = document.getElementById('submitPhoto').getAttribute('src');

    const getListed = this.returnPersonListed();

    this.newPerson = new Person();
    this.newPerson.personImg = photoInp;
    this.newPerson.fname = addName.value;
    this.newPerson.lname = addSurname.value;
    this.newPerson.personListed = getListed;
    this.newPerson.personCreated = new Date();

    this.personService.addPerson(this.newPerson)
      .subscribe(value => {
        // console.log(value);
      }, error => console.log(error));
    this.gotoList();
  }

  onSubmit() {
    this.addPerson();
  }

  gotoList() {
    this.SpinnerService.show();
    setTimeout(() => {
      this.SpinnerService.hide();
    }, 500);
    // location.reload();
    /*if (this.newPerson.personListed === 'White')
    {
      this.router.navigate(['/people-white']);
    }
    else
    {
      this.router.navigate(['/people-black']);
    }*/
  }
}
