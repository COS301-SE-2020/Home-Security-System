import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PeopleWhiteComponent } from './people-white.component';

describe('PeopleWhiteComponent', () => {
  let component: PeopleWhiteComponent;
  let fixture: ComponentFixture<PeopleWhiteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PeopleWhiteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PeopleWhiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
