import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedBlackComponent } from './deleted-black.component';

describe('DeletedBlackComponent', () => {
  let component: DeletedBlackComponent;
  let fixture: ComponentFixture<DeletedBlackComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletedBlackComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletedBlackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
