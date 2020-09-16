import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login.component';
import { RouterModule } from '@angular/router';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [HttpClientModule, RouterModule.forRoot([])]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should render title in a h1 tag', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to');
  });
  it('ensure close button appears', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('i').textContent).toContain('close');
  });
  it('forgot password link to be set', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('span').textContent).toContain('Forgot password?');
  });
  it('login button should appear', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('#login-button').textContent).toContain('Log in');
  });
  it('recover password modals should appear', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.forgotExplain').textContent).toContain('Enter email and we\'ll send you a' +
      ' link to get back into your account.');
  });

  /*it('redirect should appear', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.loginButton loginInputs loginButton').textContent).toContain('Next');
  });*/

});
