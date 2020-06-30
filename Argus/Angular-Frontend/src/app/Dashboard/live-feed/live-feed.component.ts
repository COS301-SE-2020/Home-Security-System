import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import {WebcamImage, WebcamUtil} from "ngx-webcam";
import {Observable, Subject} from "rxjs";

@Component({
selector: 'app-live-feed',
templateUrl: './live-feed.component.html',
styleUrls: ['./live-feed.component.css']
})
export class LiveFeedComponent implements OnInit {
title = 'Angular-Frontend';

@ViewChild('video')
public webcam: ElementRef;

@ViewChild('canvas')
public canvas: ElementRef;

public captures: Array<any>;

public showCam = true;

public camImg: WebcamImage = null;

public snap_trigger: Subject<void> = new Subject<void>();

public trigger_s(): void {
  this.snap_trigger.next();
}

public handleShot(img: WebcamImage): void {
  console.info('received webcam image', img);
  this.camImg = img;
}

ngOnInit(): void {
}

public toggleCam(): void {
  this.showCam = !this.showCam;
}

public get triggerObservable(): Observable<void> {
  return this.snap_trigger.asObservable();
}

}
