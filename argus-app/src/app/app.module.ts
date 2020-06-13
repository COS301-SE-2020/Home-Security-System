import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//added
import { Routes, RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListUsersComponent } from './Users/list-users.component';
import { ListVehiclesComponent } from './Vehicles/list-vehicles.component';
import { ListPeopleComponent } from './Person/list-people.component';
import { UpdatePersonComponent } from './Person/update-person.component';
import { UpdateVehicleComponent } from './Vehicles/update-vehicle.component';
import { AddVehicleComponent } from './Vehicles/add-vehicle.component';
import { AddPersonComponent } from './Person/add-person.component';
import { SideNavComponent } from './side-nav/side-nav.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { WhiteListComponent } from './white-list/white-list.component';
import { BlackListComponent } from './black-list/black-list.component';
import { FooterComponent } from './footer/footer.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { LicensePlatesComponent } from './license-plates/license-plates.component';

const appRoutes: Routes =[
  {path: 'notifications', component: NotificationsComponent },
  {path: 'people', component: ListPeopleComponent },
  {path: 'vehicle', component: ListVehiclesComponent },
  {path: 'users', component: ListUsersComponent },
  {path: 'addp', component: AddPersonComponent },
  {path: 'addv', component: AddVehicleComponent },
  {path: 'editp', component: UpdatePersonComponent },
  {path: 'editv', component: UpdateVehicleComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    ListUsersComponent,
    ListVehiclesComponent,
    ListPeopleComponent,
    UpdatePersonComponent,
    UpdateVehicleComponent,
    AddVehicleComponent,
    AddPersonComponent,
    SideNavComponent,
    TopBarComponent,
    NotificationsComponent,
    WhiteListComponent,
    BlackListComponent,
    FooterComponent,
    DashboardComponent,
    UserProfileComponent,
    LicensePlatesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
    RouterModule.forRoot(appRoutes),
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
