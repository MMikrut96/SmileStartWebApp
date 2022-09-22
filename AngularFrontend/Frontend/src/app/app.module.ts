import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { JwtModule } from '@auth0/angular-jwt';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { DriverListComponent } from './sites/driver-list/driver-list.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './sites/home/home.component';
import { ContactComponent } from './sites/contact/contact.component';
import { AboutCoopComponent } from './sites/about-coop/about-coop.component';
import { SignInComponent } from './sites/sign-in/sign-in.component';
import { HttpErrorInterceptor } from './config/http-error-interceptor';
import { AddDriverComponent } from './sites/add-driver/add-driver.component';
import { VehicleListComponent } from './sites/vehicle-list/vehicle-list.component';
import { AddVehicleComponent } from './sites/add-vehicle/add-vehicle.component';
import { ProfitListComponent } from './sites/profit-list/profit-list.component';
import { RentedVehicleComponent } from './sites/rented-vehicle/rented-vehicle.component';


@NgModule({
  declarations: [
    AppComponent,
    DriverListComponent,
    NavbarComponent,
    HomeComponent,
    ContactComponent,
    AboutCoopComponent,
    SignInComponent,
    AddDriverComponent,
    VehicleListComponent,
    AddVehicleComponent,
    ProfitListComponent,
    RentedVehicleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    JwtModule,
    NgbModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorInterceptor,
      multi: true
    }
  ],  // services
  bootstrap: [AppComponent]  // components
})
export class AppModule { }
