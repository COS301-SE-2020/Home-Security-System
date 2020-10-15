import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {DashboardComponent} from './Dashboard/dashboard/dashboard.component';
import {LoginComponent} from './Dashboard/login/login.component';
import { LogoutComponent } from './Dashboard/logout/logout.component';
import {SettingsComponent} from './Dashboard/settings/settings.component';
import {ViewUserComponent} from './User/view-user/view-user.component';
import {AddUserComponent} from './User/add-user/add-user.component';
import {EditUserComponent} from './User/edit-user/edit-user.component';
import {DeletedUsersComponent} from './User/deleted-users/deleted-users.component';
import {ListUsersComponent} from './User/list-users/list-users.component';
import {UserProfileComponent} from './User/user-profile/user-profile.component';
import {PeopleWhiteComponent} from './Person/people-white/people-white.component';
import {PeopleBlackComponent} from './Person/people-black/people-black.component';
import {PeopleGreyComponent} from './Person/people-grey/people-grey.component';
import {AddPersonComponent} from './Person/add-person/add-person.component';
import {EditPersonComponent} from './Person/edit-person/edit-person.component';
import {DeletedWhiteComponent} from './Person/deleted-white/deleted-white.component';
import {DeletedBlackComponent} from './Person/deleted-black/deleted-black.component';
import {NotificationComponent} from './Dashboard/notification/notification.component';

import { AuthGuardService } from './model/auth-guard.service';

const routes: Routes = [
  {path: '', redirectTo: 'login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent, data: {title: 'Login | Argus'}},
  {path: 'logout', component: LogoutComponent, data: {title: 'Logout | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'dashboard', component: DashboardComponent, data: {title: 'Dashboard | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'user-profile', component: UserProfileComponent, data: {title: 'User Profile | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'user-list', component: ListUsersComponent, data: {title: 'User List | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'add-user', component: AddUserComponent, data: {title: 'Add User | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'deleted-users', component: DeletedUsersComponent, data: {title: 'Deleted Users | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'edit-user/:id', component: EditUserComponent, data: {title: 'Edit User | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'view-user/:id', component: ViewUserComponent, data: {title: 'View User | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'people-cleared', component: PeopleWhiteComponent, data: {title: 'Person White-List | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'people-unknown', component: PeopleGreyComponent, data: {title: 'Person Grey-List | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'people-threat', component: PeopleBlackComponent, data: {title: 'Person Black-List | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'deleted-cleared', component: DeletedWhiteComponent, data: {title: 'Deleted Person White-List | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'deleted-threat', component: DeletedBlackComponent, data: {title: 'Deleted Person Black-List | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'add-person', component: AddPersonComponent, data: {title: 'Add Person | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'edit-person/:id', component: EditPersonComponent, data: {title: 'Edit Person | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'settings', component: SettingsComponent, data: {title: 'Notifications | Argus'},
    canActivate: [AuthGuardService]},
  {path: 'notification', component: NotificationComponent, data: {title: 'Notifications | Argus'},
    canActivate: [AuthGuardService]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
