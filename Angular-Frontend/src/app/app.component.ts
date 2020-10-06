import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { TitleService } from './title.service';
import { LoginComponent } from './Dashboard/login/login.component';
import { ResetPasswordComponent } from './Dashboard/reset-password/reset-password.component';
import { environment } from '../environments/environment';

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

  enforceHTTPS()
  {
    if (environment.production) {
      if(location.protocol === 'http:') {
        window.location.href = location.href.replace('http', 'https');
      }
    }
  }

  ngOnInit() {

    const appTitle = this.titleService.getTitle();
    this.enforceHTTPS();
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => {
        let child = this.activatedRoute.firstChild;

        // console.log(this.isLoginPage());
        if ( this.isLoginPage() === true || this.isResetPasswordPage() === true )
        {
          document.getElementById('displayType').className = 'login-panel';
          document.getElementById('app-footer').hidden = true;
        }
        else
        {
          document.getElementById('displayType').className = 'main-panel';
          document.getElementById('app-footer').hidden = true;
        }

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
