import { Component, OnInit } from '@angular/core';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { UserProfileComponent } from '../user-profile/user-profile.component';
import { WhiteListComponent } from '../white-list/white-list.component';
import { BlackListComponent } from '../black-list/black-list.component';
import { NotificationsComponent } from '../notifications/notifications.component';
import { Routes } from '@angular/router';

@Component({
  selector: 'app-side-nav',
  templateUrl: './side-nav.component.html',
  styleUrls: ['./side-nav.component.css']
})

export class SideNavComponent implements OnInit {

  constructor() { }
  ngOnInit(): void {
  }
}