import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from "@angular/common";
// added
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { WebcamModule } from 'ngx-webcam';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxSpinnerModule } from 'ngx-spinner';
import { environment } from '../environments/environment';

import { LiveFeedComponent } from './Dashboard/live-feed/live-feed.component';
import { DashboardComponent } from './Dashboard/dashboard/dashboard.component';
import { FooterComponent } from './Dashboard/footer/footer.component';
import { LoginComponent } from './Dashboard/login/login.component';
import { SettingsComponent } from './Dashboard/settings/settings.component';
import { SideNavComponent } from './Dashboard/side-nav/side-nav.component';
import { TopNavComponent } from './Dashboard/top-nav/top-nav.component';
import { NotificationComponent } from './Dashboard/notification/notification.component';
import { ResetPasswordComponent } from './Dashboard/reset-password/reset-password.component';
import { PeopleGreyComponent } from './Person/people-grey/people-grey.component';
import { PeopleWhiteComponent } from './Person/people-white/people-white.component';
import { PeopleBlackComponent } from './Person/people-black/people-black.component';
import { AddPersonComponent } from './Person/add-person/add-person.component';
import { EditPersonComponent } from './Person/edit-person/edit-person.component';
import { ViewUserComponent } from './User/view-user/view-user.component';
import { AddUserComponent } from './User/add-user/add-user.component';
import { EditUserComponent } from './User/edit-user/edit-user.component';
import { ListUsersComponent } from './User/list-users/list-users.component';
import { UserProfileComponent } from './User/user-profile/user-profile.component';
import { DeletedUsersComponent } from './User/deleted-users/deleted-users.component';
import { DeletedWhiteComponent } from './Person/deleted-white/deleted-white.component';
import { DeletedBlackComponent } from './Person/deleted-black/deleted-black.component';
import { AddVehicleComponent } from './Vehicle/add-vehicle/add-vehicle.component';
import { EditVehicleComponent } from './Vehicle/edit-vehicle/edit-vehicle.component';
import { VehicleBlackComponent } from './Vehicle/vehicle-black/vehicle-black.component';
import { VehicleWhiteComponent } from './Vehicle/vehicle-white/vehicle-white.component';
import { VehicleGreyComponent } from './Vehicle/vehicle-grey/vehicle-grey.component';
import { RemovedBlackComponent } from './Vehicle/removed-black/removed-black.component';
import { RemovedWhiteComponent } from './Vehicle/removed-white/removed-white.component';

import { NotificationService } from './model/notification.service';
import { UserService } from './model/user.service';
import { PersonService } from './model/person.service';
import { VehicleService } from './model/vehicle.service';
import { ImageService } from './model/image.service';
import { SessionService } from './model/session.service';

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
    ViewUserComponent,
    ListUsersComponent,
    UserProfileComponent,
    PeopleWhiteComponent,
    NotificationComponent,
    ResetPasswordComponent,
    DeletedUsersComponent,
    DeletedWhiteComponent,
    DeletedBlackComponent,
    AddVehicleComponent,
    EditVehicleComponent,
    VehicleBlackComponent,
    VehicleWhiteComponent,
    VehicleGreyComponent,
    RemovedBlackComponent,
    RemovedWhiteComponent
  ],
  imports: [
      BrowserModule,
      BrowserAnimationsModule,
      AppRoutingModule,
      WebcamModule,
      RouterModule.forRoot(appRoutes),
      HttpClientModule,
      FormsModule,
      ReactiveFormsModule,
      NgxSpinnerModule
  ],
  providers: [
    {provide: LocationStrategy, useClass: HashLocationStrategy},
    NotificationService,
    UserService,
    PersonService,
    VehicleService,
    ImageService,
    SessionService],
  bootstrap: [AppComponent]
})
export class AppModule { }


