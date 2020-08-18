import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletedWhiteComponent } from './deleted-white.component';

describe('DeletedWhiteComponent', () => {
  let component: DeletedWhiteComponent;
  let fixture: ComponentFixture<DeletedWhiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DeletedWhiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DeletedWhiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  /*it('should create', () => {
    expect(component).toBeTruthy();
  });*/
});
