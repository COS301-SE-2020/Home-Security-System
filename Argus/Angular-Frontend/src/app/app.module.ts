
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// added
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import {WebcamModule} from 'ngx-webcam';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LiveFeedComponent } from './Dashboard/live-feed/live-feed.component';
import { DashboardComponent } from './Dashboard/dashboard/dashboard.component';
import { FooterComponent } from './Dashboard/footer/footer.component';
import { LoginComponent } from './Dashboard/login/login.component';
import { SettingsComponent } from './Dashboard/settings/settings.component';
import { SideNavComponent } from './Dashboard/side-nav/side-nav.component';
import { TopNavComponent } from './Dashboard/top-nav/top-nav.component';
import { NotificationComponent } from './Dashboard/notification/notification.component';
import { ResetPasswordComponent } from './Dashboard/reset-password/reset-password.component';
import { PeopleGreyComponent } from './Grey-list/people-grey/people-grey.component';
import { PeopleWhiteComponent } from './White-list/people-white/people-white.component';
import { PeopleBlackComponent } from './Black-list/people-black/people-black.component';
import { AddPersonComponent } from './Person/add-person/add-person.component';
import { EditPersonComponent } from './Person/edit-person/edit-person.component';
import { AddUserComponent } from './User/add-user/add-user.component';
import { EditUserComponent } from './User/edit-user/edit-user.component';
import { UserListComponent } from './User/user-list/user-list.component';
import { UserProfileComponent } from './User/user-profile/user-profile.component';
import { DeletedUsersComponent } from './User/deleted-users/deleted-users.component';
import { DeletedWhiteComponent } from './Person/deleted-white/deleted-white.component';
import { DeletedBlackComponent } from './Person/deleted-black/deleted-black.component';

import { NotificationService } from './model/notification.service';
import { UserService } from './model/user.service';
import { PersonService } from './model/person.service';
import { VehicleService } from './model/vehicle.service';
import { ImageService } from './model/image.service';

import {environment} from '../environments/environment';

const appRoutes: Routes = [];

@NgModule({
  declarations: [
    AppComponent,
    LiveFeedComponent,
    PeopleBlackComponent,
    DashboardComponent,
    FooterComponent,
    LoginComponent,
    SettingsComponent,
    SideNavComponent,
    TopNavComponent,
    PeopleGreyComponent,
    AddPersonComponent,
    EditPersonComponent,
    AddUserComponent,
    EditUserComponent,
    UserListComponent,
    UserProfileComponent,
    PeopleWhiteComponent,
    NotificationComponent,
    ResetPasswordComponent,
    DeletedUsersComponent,
    DeletedWhiteComponent,
    DeletedBlackComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    WebcamModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [NotificationService,
    UserService,
    PersonService,
    VehicleService,
    ImageService],
  bootstrap: [AppComponent]
})
export class AppModule { }


