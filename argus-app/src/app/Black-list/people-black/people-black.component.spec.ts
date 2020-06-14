import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeopleBlackComponent } from './people-black.component';

describe('PeopleBlackComponent', () => {
  let component: PeopleBlackComponent;
  let fixture: ComponentFixture<PeopleBlackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeopleBlackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeopleBlackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
