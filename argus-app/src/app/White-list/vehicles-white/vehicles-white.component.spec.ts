import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesWhiteComponent } from './vehicles-white.component';

describe('VehiclesWhiteComponent', () => {
  let component: VehiclesWhiteComponent;
  let fixture: ComponentFixture<VehiclesWhiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiclesWhiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclesWhiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
