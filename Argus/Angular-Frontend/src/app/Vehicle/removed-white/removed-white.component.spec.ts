import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemovedWhiteComponent } from './removed-white.component';

describe('RemovedWhiteComponent', () => {
  let component: RemovedWhiteComponent;
  let fixture: ComponentFixture<RemovedWhiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemovedWhiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemovedWhiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
