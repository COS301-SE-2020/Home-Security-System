import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GreyListComponent } from './grey-list.component';

describe('GreyListComponent', () => {
  let component: GreyListComponent;
  let fixture: ComponentFixture<GreyListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GreyListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GreyListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
