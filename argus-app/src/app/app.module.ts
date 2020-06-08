import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NotificationsComponent } from './notifications/notifications.component';
import { ListPeopleComponent } from './Person/list-people/list-people.component';
import { ListVehiclesComponent } from './Vehicle/list-vehicles/list-vehicles.component';
import { ListUsersComponent } from './User/list-users/list-users.component';

@NgModule({
  declarations: [
    AppComponent,
    NotificationsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
