import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleBlackComponent } from './vehicle-black.component';

describe('VehicleBlackComponent', () => {
  let component: VehicleBlackComponent;
  let fixture: ComponentFixture<VehicleBlackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleBlackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleBlackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /*it('should create', () => {
    expect(component).toBeTruthy();
  });*/
});
