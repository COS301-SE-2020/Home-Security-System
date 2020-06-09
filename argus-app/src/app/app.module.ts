import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//added
import { Routes, RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { ListPeopleComponent } from './Person/list-people/list-people.component';
import { ListVehiclesComponent } from './Vehicle/list-vehicles/list-vehicles.component';
import { ListUsersComponent } from './User/list-users/list-users.component';
import { AddPersonComponent } from './Person/add-person/add-person.component';
import { UpdatePersonComponent } from './Person/update-person/update-person.component';
import { AddVehicleComponent } from './Vehicle/add-vehicle/add-vehicle.component';
import { UpdateVehicleComponent } from './Vehicle/update-vehicle/update-vehicle.component';


//added
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
    NotificationsComponent
  ],
  imports: [
    BrowserModule,
    //added
    RouterModule.forRoot(appRoutes),
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
