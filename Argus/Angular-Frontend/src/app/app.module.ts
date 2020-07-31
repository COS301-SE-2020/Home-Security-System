
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
// added
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { FormsModule, FormBuilder, FormGroup } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import {WebcamModule} from 'ngx-webcam';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { LiveFeedComponent } from './Dashboard/live-feed/live-feed.component';
import { PeopleBlackComponent } from './Black-list/people-black/people-black.component';
import { VehiclesBlackComponent } from './Black-list/vehicles-black/vehicles-black.component';
import { DashboardComponent } from './Dashboard/dashboard/dashboard.component';
import { FooterComponent } from './Dashboard/footer/footer.component';
import { LoginComponent } from './Dashboard/login/login.component';
import { SettingsComponent } from './Dashboard/settings/settings.component';
import { SideNavComponent } from './Dashboard/side-nav/side-nav.component';
import { TopNavComponent } from './Dashboard/top-nav/top-nav.component';
import { PeopleGreyComponent } from './Grey-list/people-grey/people-grey.component';
import { VehiclesGreyComponent } from './Grey-list/vehicles-grey/vehicles-grey.component';
import { AddPersonComponent } from './Person/add-person/add-person.component';
import { UpdatePersonComponent } from './Person/update-person/update-person.component';
import { AddUserComponent } from './User/add-user/add-user.component';
import { EditUserComponent } from './User/edit-user/edit-user.component';
import { ListUsersComponent } from './User/list-users/list-users.component';
import { UserProfileComponent } from './User/user-profile/user-profile.component';
import { AddVehicleComponent } from './Vehicle/add-vehicle/add-vehicle.component';
import { EditVehicleComponent } from './Vehicle/edit-vehicle/edit-vehicle.component';
import { PeopleWhiteComponent } from './White-list/people-white/people-white.component';
import { VehiclesWhiteComponent } from './White-list/vehicles-white/vehicles-white.component';
import { NotificationComponent } from './Dashboard/notification/notification.component';
import { ResetPasswordComponent } from './Dashboard/reset-password/reset-password.component';

import { NotificationService } from './model/notification.service';
import { UsersService } from './model/users.service';
import { PersonService } from './model/person.service';
import { VehicleService } from './model/vehicle.service';

import {environment} from '../environments/environment';
// import {AuthInterceptor} from './model/auth.interceptor';

const appRoutes: Routes = [];

@NgModule({
  declarations: [
    AppComponent,
    LiveFeedComponent,
    PeopleBlackComponent,
    VehiclesBlackComponent,
    DashboardComponent,
    FooterComponent,
    LoginComponent,
    SettingsComponent,
    SideNavComponent,
    TopNavComponent,
    PeopleGreyComponent,
    VehiclesGreyComponent,
    AddPersonComponent,
    UpdatePersonComponent,
    AddUserComponent,
    EditUserComponent,
    ListUsersComponent,
    UserProfileComponent,
    AddVehicleComponent,
    EditVehicleComponent,
    PeopleWhiteComponent,
    VehiclesWhiteComponent,
    NotificationComponent,
    ResetPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    WebcamModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule,
    FormsModule,
    FormsModule
  ],
  providers: [NotificationService,
    UsersService,
    PersonService,
    VehicleService,
    /*AuthService,
    {
     provide: HTTP_INTERCEPTORS,
     useClass: AuthInterceptor,
     multi: true
    }*/],
  bootstrap: [AppComponent]
})
export class AppModule { }


