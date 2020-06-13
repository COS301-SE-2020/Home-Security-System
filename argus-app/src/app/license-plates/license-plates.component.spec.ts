import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LicensePlatesComponent } from './license-plates.component';

describe('LicensePlatesComponent', () => {
  let component: LicensePlatesComponent;
  let fixture: ComponentFixture<LicensePlatesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LicensePlatesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LicensePlatesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
