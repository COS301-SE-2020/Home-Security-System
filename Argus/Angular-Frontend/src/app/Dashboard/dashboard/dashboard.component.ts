import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {WebcamImage} from 'ngx-webcam';
import {Observable, Subject} from 'rxjs';
import {TitleService} from '../../title.service';
import {PersonService} from '../../model/person.service';
import {Person} from '../../model/person';
import * as CanvasJS from '../../../assets/js/canvasjs.min';
import {Notification} from '../../model/notification';
import {NotificationService} from '../../model/notification.service';
// import {getLocaleDateFormat} from '@angular/common';
// import {UserService} from '../../model/user.service';
// import {User} from '../../model/user';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {

  constructor(private appService: TitleService, private personService: PersonService, private notificationService: NotificationService) {}

  people: Observable<Person[]>;
  currentDate = new Date();

  notification: Observable<Notification[]>;
  note: Notification;

  // ----------------------
  title = 'Dashboard';

  @ViewChild('video')
  public webcam: ElementRef;

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

  // ==========================================================================================

  public calculateNumberOfPeople(): void{
    this.personService.getPersonList().subscribe(data => {
      let threat = 0;
      let cleared = 0;
      let unknown = 0;
      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < data.length; i++){
        if (data[i].personListed === 'Grey'){
          unknown++;
        }
        else if (data[i].personListed === 'Black'){
          threat++;
        }
        else{
          cleared++;
        }
      }
      this.pieChart(cleared, unknown, threat);
    });
  }

  // ==========================================================================================

  public calculateNumberOfNotifications(): void{
    this.notificationService.getNotificationList().subscribe(data => {

      // console.log(data[0].onDate);
      // console.log( this.dayTester(0));

      let six = 0;
      let five = 0;
      let four = 0;
      let three = 0;
      let two = 0;
      let one = 0;
      let today = 0;

      // tslint:disable-next-line:prefer-for-of
      for (let i = 0; i < data.length; i++){
        if (data[i].onDate === this.dayTester(0)){
          today++;
        }
        else if (data[i].onDate === this.dayTester(1)){
          one++;
        }
        else if (data[i].onDate === this.dayTester(2)){
          two++;
        }
        else if (data[i].onDate === this.dayTester(3)){
          three++;
        }
        else if (data[i].onDate === this.dayTester(4)){
          four++;
        }
        else if (data[i].onDate === this.dayTester(5)){
          five++;
        }
        else if (data[i].onDate === this.dayTester(6)){
          six++;
        }
        else{
        }
      }
      this.barchart(six, five, four, three, two, one, today );
    });
  }

  // ==========================================================================================

  public dayTester( days ) {
    const date = new Date();
    const last = new Date(date.getTime() - (days * 24 * 60 * 60 * 1000));
    const day = last.getDate();
    const month = 1 + last.getMonth();
    let monthSmall = '';
    let daySmall = '';

    if ( month < 10 )
    {
      monthSmall = ('0' + month).toString();
    }
    if ( day < 10 )
    {
      daySmall = ('0' + month).toString();
    }

    const year = last.getFullYear();

    if ( monthSmall === '' && daySmall === '')
    {
      return (year + '-' + month + '-' + day).toString();
    }
    else if (monthSmall === '' && daySmall !== '')
    {
      return (year + '-' + month + '-' + daySmall).toString();
    }
    else if ( monthSmall !== '' && daySmall === '')
    {
      return (year + '-' + monthSmall + '-' + day).toString();
    }
    else if (monthSmall !== '' && daySmall !== '')
    {
      return (year + '-' + monthSmall + '-' + daySmall).toString();
    }
    else
    {
      return (year + '-' + month + '-' + day).toString();
    }
  }

  // ==========================================================================================

  public getCorrectDay( days ) {
    const date = new Date();
    const last = new Date(date.getTime() - (days * 24 * 60 * 60 * 1000));
    const day = last.getDate();
    const month = 1 + last.getMonth();
    // const year = last.getFullYear();
    return (day + '/' + month).toString();
  }

  // ==========================================================================================

  public barchart(Mon, Tue, Wed, Thu, Fri, Sat, Sun): void{
    const date = new Date();

    const chart = new CanvasJS.Chart('chartContainer', {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: 'Notifications'
      },
      data: [{
        type: 'column',
        dataPoints: [
          { y: Mon, label: this.getCorrectDay(6)},
          { y: Tue, label: this.getCorrectDay(5)},
          { y: Wed, label: this.getCorrectDay(4)},
          { y: Thu, label: this.getCorrectDay(3)},
          { y: Fri, label: this.getCorrectDay(2)},
          { y: Sat, label: this.getCorrectDay(1)},
          { y: Sun, label: this.getCorrectDay(0)}
        ]
      }]
    });

    chart.render();
  }

  // ==========================================================================================

  public pieChart(val1, val2, val3): void{
    const chart = new CanvasJS.Chart('chartContainer2', {
      // light2 or dark2
      theme: 'light2',
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: 'Listed people'
      },
      data: [{
        type: 'doughnut',
        showInLegend: true,
        toolTipContent: '<b>{name}</b>: {y} (#percent%)',
        indexLabel: '{name} - #percent%',
        dataPoints: [
          { y: val2, name: 'Unknown' },
          { y: val1, name: 'Cleared' },
          { y: val3, name: 'Threat' }
        ]
      }]
    });

    chart.render();
  }

// ==========================================================================================
  // private ChartItems: ChartService;

  ngOnInit(): void {
    this.appService.setTitle('Dashboard');
    // this.getChartDetails();
    // this.barchart();
    // this.pieChart();
    this.calculateNumberOfPeople();
    this.calculateNumberOfNotifications();
  }

  public toggleCam(): void {
    this.showCam = !this.showCam;
  }

  public get triggerObservable(): Observable<void> {
    return this.snapTrigger.asObservable();
  }

}
