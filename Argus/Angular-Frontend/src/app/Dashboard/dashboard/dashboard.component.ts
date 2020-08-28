import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {WebcamImage} from 'ngx-webcam';
import {Observable, Subject} from 'rxjs';
import {TitleService} from '../../title.service';
import * as CanvasJS from '../../../assets/js/canvasjs.min';
// import {Chart} from 'chart.js';
// import {ChartService} from '../charts/chart.service';
import {getLocaleDateFormat} from '@angular/common';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit
{
  currentDate = new Date();

  // ----------------------
  title = 'argus-app';

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
  public chart1(): void{
    const chart = new CanvasJS.Chart('chartContainer', {
      animationEnabled: true,
      exportEnabled: true,
      title: {
        text: 'Notifications'
      },
      data: [{
        type: 'column',
        dataPoints: [
          { y: 5, label: 'Monday' },
          { y: 4, label: 'Tuesday' },
          { y: 3, label: 'Wednesday' },
          { y: 2, label: 'Thursday' },
          { y: 0, label: 'Friday' },
          { y: 0, label: 'Saturday' },
          { y: 0, label: 'Sunday' }
        ]
      }]
    });

    chart.render();
  }
  public pieChart(): void{
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
          { y: 450, name: 'Cleared' },
          { y: 120, name: 'Threat' },
          { y: 300, name: 'Suspicious' }
        ]
      }]
    });

    chart.render();
  }

  constructor(private appService: TitleService ) {}
  // private ChartItems: ChartService;

  ngOnInit(): void {
    this.appService.setTitle('Dashboard');
    // this.getChartDetails();
    this.chart1();
    this.pieChart();
  }

  public toggleCam(): void {
    this.showCam = !this.showCam;
  }

  public get triggerObservable(): Observable<void> {
    return this.snapTrigger.asObservable();
  }

  /*public getChartDetails() {
    this.ChartItems.getChart().subscribe(data => {
        console.log(data);
        const chartLable = [];
        const chartDetails = [];

        // tslint:disable-next-line:prefer-for-of
        for (let i = 0; i < data.chartDetails[0].chartData.length; i++) {
          chartLable.push(data.chartDetails[0].chartData[i].x);
          chartDetails.push(data.chartDetails[0].chartData[i].y);
        }


        const ctx = document.getElementById('myChart') as HTMLCanvasElement;
        ctx.getContext('2d');
        const myChart = new Chart(ctx, {
          type: 'bar',
          data: {
            labels: chartLable,
            datasets: [{
              label: '# of Votes',
              data: chartDetails,
              backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
              ],
              borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
              ],
              borderWidth: 1
            }]
          },
          options: {
            scales: {
              yAxes: [{
                ticks: {
                  beginAtZero: true
                }
              }]
            }
          }
        });
      }
    );
  }
   */

  /*
  public generateCharts()

  {
    const canvas = document.getElementById('myChart') as HTMLCanvasElement;
    canvas.getContext('webgl');

    if (!canvas) {
      canvas.getContext('experimental-webgl');
    }
    const myChart = new Chart(canvas, {
      type: 'bar',
      data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
          label: '# of Votes',
          data: [12, 19, 3, 5, 2, 3],
          backgroundColor: [
            'rgba(255, 99, 132, 0.2)',
            'rgba(54, 162, 235, 0.2)',
            'rgba(255, 206, 86, 0.2)',
            'rgba(75, 192, 192, 0.2)',
            'rgba(153, 102, 255, 0.2)',
            'rgba(255, 159, 64, 0.2)'
          ],
          borderColor: [
            'rgba(255, 99, 132, 1)',
            'rgba(54, 162, 235, 1)',
            'rgba(255, 206, 86, 1)',
            'rgba(75, 192, 192, 1)',
            'rgba(153, 102, 255, 1)',
            'rgba(255, 159, 64, 1)'
          ],
          borderWidth: 1
        }]
      },
      options: {
        scales: {
          yAxes: [{
            ticks: {
              beginAtZero: true
            }
          }]
        }
      }
    });
  }
   */

}
