/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import {AngularFireDatabase, AngularFireList} from 'angularfire2/database';
import { NotificationComponent } from './notification.component';
import construct = Reflect.construct;

describe('NotificationComponent', () => {
  let component: NotificationComponent;
  let fixture: ComponentFixture<NotificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotificationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  /*
  it('if it runs, should expect surnames ',  () => {

    const notComp = new NotificationComponent();
    const mess = notComp.ReadDB();
    expect(mess).toBe('success');
    //console.log = jasmine.createSpy('log');
    //const outp = 'success';
    //expect(console.log).toHaveBeenCalledWith(outp);
    //expect(notComp.ReadDB()).message().toMatch('success');

  });
   */
/*
  it('should create', () => {
    expect(component).toBeTruthy();
  });
   */
});
