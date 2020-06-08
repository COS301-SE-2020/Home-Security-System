import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NotificationsComponent } from './notifications/notifications.component';
import { ListPeopleComponent } from './Person/list-people/list-people.component';
import { ListVehiclesComponent } from './Vehicle/list-vehicles/list-vehicles.component';
import { ListUsersComponent } from './User/list-users/list-users.component';

/*when you want to view the various pages just make the path: ''*/
const routes: Routes = [
  {path: 'a', component: NotificationsComponent},
  {path: '', component: ListPeopleComponent},
  {path: 'c', component: ListVehiclesComponent},
  {path: 'd', component: ListUsersComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
