import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
// added
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';
import { WebcamModule } from 'ngx-webcam';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxSpinnerModule } from 'ngx-spinner';

import { DashboardComponent } from './Dashboard/dashboard/dashboard.component';
import { FooterComponent } from './Dashboard/footer/footer.component';
import { LoginComponent } from './Dashboard/login/login.component';
import { LogoutComponent } from './Dashboard/logout/logout.component';
import { SettingsComponent } from './Dashboard/settings/settings.component';
import { SideNavComponent } from './Dashboard/side-nav/side-nav.component';
import { TopNavComponent } from './Dashboard/top-nav/top-nav.component';
import { NotificationComponent } from './Dashboard/notification/notification.component';
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

import { NotificationService } from './model/notification.service';
import { UserService } from './model/user.service';
import { PersonService } from './model/person.service';
import { NetworkService } from './model/network.service';
import { AuthService } from './model/auth.service';
import { AuthGuardService } from './model/auth-guard.service';
// import { AuthInterceptorService } from './model/auth-interceptor.service';

/*
import { RecoverPasswordQuestionComponent } from './recover-password-question/recover-password-question.component';
*/

const appRoutes: Routes = [];

@NgModule({
  declarations: [
    AppComponent,
    PeopleBlackComponent,
    DashboardComponent,
    FooterComponent,
    LoginComponent,
    LogoutComponent,
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
    DeletedUsersComponent,
    DeletedWhiteComponent,
    DeletedBlackComponent/*,
    RecoverPasswordQuestionComponent*/
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
    NetworkService,
    AuthService,
    AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }


