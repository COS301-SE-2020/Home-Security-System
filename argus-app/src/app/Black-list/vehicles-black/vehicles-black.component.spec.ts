import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesBlackComponent } from './vehicles-black.component';

describe('VehiclesBlackComponent', () => {
  let component: VehiclesBlackComponent;
  let fixture: ComponentFixture<VehiclesBlackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiclesBlackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclesBlackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
