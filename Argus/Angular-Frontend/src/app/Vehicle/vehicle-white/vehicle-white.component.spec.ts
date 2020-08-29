import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleWhiteComponent } from './vehicle-white.component';

describe('VehicleWhiteComponent', () => {
  let component: VehicleWhiteComponent;
  let fixture: ComponentFixture<VehicleWhiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehicleWhiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleWhiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
