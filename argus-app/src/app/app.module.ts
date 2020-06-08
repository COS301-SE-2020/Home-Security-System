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

//added
const appRoutes: Routes =[

{path: 'notifications', component: NotificationsComponent },
{path: 'people', component: ListPeopleComponent },
{path: 'vehicle', component: ListVehiclesComponent },
{path: 'users', component: ListUsersComponent }

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
