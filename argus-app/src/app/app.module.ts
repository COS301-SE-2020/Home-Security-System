import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListUsersComponent } from './Users/list-users.component';
import { ListVehiclesComponent } from './Vehicles/list-vehicles.component';
import { ListPeopleComponent } from './Person/list-people.component';
import { UpdatePersonComponent } from './Person/update-person.component';
import { UpdateVehicleComponent } from './Vehicles/update-vehicle.component';
import { AddVehicleComponent } from './Vehicles/add-vehicle.component';
import { AddPersonComponent } from './Person/add-person.component';

@NgModule({
  declarations: [
    AppComponent,
    ListUsersComponent,
    ListVehiclesComponent,
    ListPeopleComponent,
    UpdatePersonComponent,
    UpdateVehicleComponent,
    AddVehicleComponent,
    AddPersonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
