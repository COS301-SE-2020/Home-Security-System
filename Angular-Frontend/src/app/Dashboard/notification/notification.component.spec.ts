/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { NotificationComponent } from './notification.component';

describe('NotificationComponent', () => {
  let component: NotificationComponent;
  let fixture: ComponentFixture<NotificationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NotificationComponent ],
      imports: [HttpClientModule, RouterModule.forRoot([])]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NotificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  /*it('header should display correctly', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(NotificationComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h4').textContent).toContain('Notifications');
  });
  it('subheading should display correctly', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(NotificationComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('#cardC').textContent).toContain('A history of all the notifications sent to you');
  });
  it('searchbar should display correctly', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(NotificationComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('i').textContent).toContain('search');
  });
  it('table headers should display correctly', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(NotificationComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('#messageHeader').textContent).toContain('Message');
    expect(compiled.querySelector('#imageHeader').textContent).toContain('Image');
    expect(compiled.querySelector('#dismissHeader').textContent).toContain('Dismiss');
  });*/
});
