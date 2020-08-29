import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemovedBlackComponent } from './removed-black.component';

describe('RemovedBlackComponent', () => {
  let component: RemovedBlackComponent;
  let fixture: ComponentFixture<RemovedBlackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemovedBlackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemovedBlackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
