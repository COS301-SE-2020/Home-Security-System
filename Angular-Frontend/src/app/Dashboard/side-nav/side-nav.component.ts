import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../model/auth.service";


@Component({
selector: 'app-side-nav',
templateUrl: './side-nav.component.html',
styleUrls: ['./side-nav.component.css']
})

export class SideNavComponent implements OnInit {

  constructor(public authService:AuthService) { }
  ngOnInit(): void { }
}
