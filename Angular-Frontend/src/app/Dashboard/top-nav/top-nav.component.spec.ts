import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TopNavComponent } from './top-nav.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';

describe('TopNavComponent', () => {
  let component: TopNavComponent;
  let fixture: ComponentFixture<TopNavComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TopNavComponent ],
      imports: [HttpClientModule, RouterModule.forRoot([])]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TopNavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  /*it('top-nav toggle should display correctly', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(TopNavComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('#navTog').textContent).toContain('Toggle navigation');
  });*/

  /*
  it('should create', () => {
    expect(component).toBeTruthy();
  });
   */
});
