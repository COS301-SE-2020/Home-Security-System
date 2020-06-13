import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SideNavComponent } from './side-nav/side-nav.component';
import { WhiteListComponent } from './white-list/white-list.component';
import { BlackListComponent } from './black-list/black-list.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

const routes: Routes = [
    { path: 'dashboard', component: DashboardComponent, data: {title: 'Dashboard | Argus'}},
    { path: 'user-profile', component: UserProfileComponent, data: {title: 'User Profile | Argus'}},
    { path: 'white-list', component: WhiteListComponent, data: {title: 'White List | Argus'}},
    { path: 'black-list', component: BlackListComponent, data: {title: 'Black List | Argus'}},
    { path: 'notifications', component: NotificationsComponent, data: {title: 'Notifications | Argus'}}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
 