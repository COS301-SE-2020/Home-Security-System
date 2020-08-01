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


  ngOnInit() {
    const appTitle = this.titleService.getTitle();
    this.router
      .events.pipe(
      filter(event => event instanceof NavigationEnd),
      map(() => {
        let child = this.activatedRoute.firstChild;

        console.log(this.isLoginPage());
        if ( this.isLoginPage() === true)
        {
          document.getElementById('topNav').style.visibility = 'hidden';
          document.getElementById('sideNav').style.visibility = 'hidden';
          // document.getElementById('topNav').remove();
          // document.getElementById('sideNav').remove();
        }
        else
        {
          document.getElementById('topNav').style.visibility = 'visible';
          document.getElementById('sideNav').style.visibility = 'visible';
          // document.getElementById('topNav').innerHTML = '<app-top-nav></app-top-nav>';
          // document.getElementById('sideNav').innerHTML = ' <app-side-nav></app-side-nav>';
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
