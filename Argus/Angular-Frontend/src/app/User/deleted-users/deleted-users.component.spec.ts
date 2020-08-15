import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedUserComponent } from './deleted-users.component';

describe('DeletedUserComponent', () => {
  let component: DeletedUserComponent;
  let fixture: ComponentFixture<DeletedUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletedUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletedUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
