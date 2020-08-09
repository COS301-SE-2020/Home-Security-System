import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { TitleService } from './title.service';
import {LoginComponent} from './Dashboard/login/login.component';
import {ResetPasswordComponent} from './Dashboard/reset-password/reset-password.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'Angular-Frontend';
  constructor(private titleService: Title, private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  setDocTitle(title: string) {
    this.titleService.setTitle(title);
  }

  isLoginPage(): boolean {
    const child = this.activatedRoute.firstChild;
    return child.component === LoginComponent;
  }

  isResetPasswordPage(): boolean {
    const child = this.activatedRoute.firstChild;
    return child.component === ResetPasswordComponent;
  }

  loginPageDisplay(): string {
    if ( this.isLoginPage() === true)
    {
      return '<div class="content">\n' +
        '      <router-outlet></router-outlet>\n' +
        '    <app-footer></app-footer>\n' +
        ' </div>';
    }
    else {
      return '<div class="main-panel">\n' +
        '\n' +
        '    <div id="navBars">\n' +
        '      <app-side-nav></app-side-nav>\n' +
        '      <app-top-nav></app-top-nav>\n' +
        '    </div>\n' +
        '\n' +
        '    <div class="content">\n' +
        '      <router-outlet></router-outlet>\n' +
        '    </div>\n' +
        '    <app-footer></app-footer>\n' +
        ' </div>';
    }
  }

  ngOnInit() {
    const appTitle = this.titleService.getTitle();
    this.router
      .events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => {
        let child = this.activatedRoute.firstChild;

        // console.log(this.isLoginPage());
        if ( this.isLoginPage() === true || this.isResetPasswordPage() === true )
        {
          document.getElementById('navBars').style.visibility = 'hidden';
          document.getElementById('displayType').className = 'content';
          document.getElementById('footerText1').style.color = 'white';
          document.getElementById('footerText2').style.color = 'white';
          document.getElementById('footerText3').style.color = 'white';
        }
        else
        {
          document.getElementById('navBars').style.visibility = 'visible';
          document.getElementById('displayType').className = 'main-panel';
          document.getElementById('footerText1').style.color = 'black';
          document.getElementById('footerText2').style.color = 'black';
          document.getElementById('footerText3').style.color = 'black';
        }

        // const loginVal = this.loginPageDisplay();
        // console.log(loginVal);

        while (child.firstChild) {
          child = child.firstChild;
        }
        if (child.snapshot.data.title) {
          return child.snapshot.data.title;
        }
        return appTitle;
      })
    ).subscribe((ttl: string) => {
      this.titleService.setTitle(ttl);
    });
  }
}
