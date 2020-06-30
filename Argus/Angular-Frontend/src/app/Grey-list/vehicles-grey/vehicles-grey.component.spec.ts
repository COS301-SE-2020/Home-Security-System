import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VehiclesGreyComponent } from './vehicles-grey.component';

describe('VehiclesGreyComponent', () => {
  let component: VehiclesGreyComponent;
  let fixture: ComponentFixture<VehiclesGreyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VehiclesGreyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VehiclesGreyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
