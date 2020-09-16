import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleGreyComponent } from './vehicle-grey.component';

describe('VehicleGreyComponent', () => {
  let component: VehicleGreyComponent;
  let fixture: ComponentFixture<VehicleGreyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleGreyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleGreyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /*it('should create', () => {
    expect(component).toBeTruthy();
  });*/
});
