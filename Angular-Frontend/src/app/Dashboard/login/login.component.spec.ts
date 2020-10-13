import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login.component';
import { RouterModule } from '@angular/router';
import {Observable} from 'rxjs';
import {User} from '../../model/user';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  // let users: Observable<User[]>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [HttpClientModule, RouterModule.forRoot([])]
    }).compileComponents();
  }));

  function loginDetails(userEmail, userPassword) {
    const email = document.getElementById('emailInput') as HTMLInputElement;
    const pass = document.getElementById('passwordField') as HTMLInputElement;
    email.value = userEmail;
    pass.value = userPassword;
    /*component.l.controls['username'].setValue(userEmail);
    component.loginForm.controls['password'].setValue(userPassword);*/
  }

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('component login fail', () => {
    // const btn = document.getElementById('loginBtn') as HTMLButtonElement;
    // loginDetails('','');

    /*
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();

    const b1 = fixture.debugElement.query(by.getElementById('loginBtn'));
    b1.nativeElement.click();
    spyOn(window, "alert");
    fixture.detectChanges();
    expect(
      window.alert
    ).toEqual('The password you entered seems to be incorrect, please retry entering your password.');
    */
    /*
    spyOn(window, "alert");
    btn.click();
    expect(window.alert).toHaveBeenCalledWith("The password you entered seems to be incorrect, please retry entering your password.");
    */
    // expect(btn.onclick )
    // expect(component.makeSession).toBeFalsy();
  });

  it('component login success', () => {
    // const btn = document.getElementById('loginBtn') as HTMLButtonElement;
    // loginDetails('Sigma','COS301Sigma!');
    // expect(btn.onclick).toBeTruthy();
    // expect(component.makeSession).toBeTrue();
  });

  it('should render title in a h1 tag', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to');
  });

  /*
  it('ensure close button appears', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('i').textContent).toContain('close');
  });
  */

  it('forgot password link to be set', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('span').textContent).toContain('Forgot password?');
  });

  /*
  it('login button should appear', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('#login-button').textContent).toContain('Log in');
  });
  */

  it('recover password modals should appear', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.forgotExplain').textContent).toContain('Enter email and we\'ll send you a' +
      ' link to get back into your account.');
  });

  /*
  it('redirect should appear', () => {
    // tslint:disable-next-line:no-shadowed-variable
    const fixture = TestBed.createComponent(LoginComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('.loginButton loginInputs loginButton').textContent).toContain('Next');
  });
  */
});
