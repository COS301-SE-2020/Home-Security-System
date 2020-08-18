import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeopleGreyComponent } from './people-grey.component';

describe('PeopleGreyComponent', () => {
  let component: PeopleGreyComponent;
  let fixture: ComponentFixture<PeopleGreyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeopleGreyComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeopleGreyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /*it('should create', () => {
    expect(component).toBeTruthy();
  });*/
});
