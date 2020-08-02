import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { TitleService } from './title.service';
import {LoginComponent} from './Dashboard/login/login.component';


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
        if ( this.isLoginPage() === true)
        {
          document.getElementById('navBars').style.visibility = 'hidden';
        }
        else
        {
          document.getElementById('navBars').style.visibility = 'visible';
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
