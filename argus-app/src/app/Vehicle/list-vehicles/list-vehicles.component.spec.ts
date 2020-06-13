import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListVehiclesComponent } from './list-vehicles.component';

describe('ListVehiclesComponent', () => {
  let component: ListVehiclesComponent;
  let fixture: ComponentFixture<ListVehiclesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListVehiclesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
