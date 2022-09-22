import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AboutCoopComponent} from './sites/about-coop/about-coop.component';
import {ContactComponent} from './sites/contact/contact.component';
import {SignInComponent} from './sites/sign-in/sign-in.component';
import {HomeComponent} from './sites/home/home.component';
import {AuthGuard} from './config/auth.guard';
import {DriverListComponent} from './sites/driver-list/driver-list.component';
import {AddDriverComponent} from './sites/add-driver/add-driver.component';
import {VehicleListComponent} from './sites/vehicle-list/vehicle-list.component';
import {AddVehicleComponent} from './sites/add-vehicle/add-vehicle.component';
import {ProfitListComponent} from './sites/profit-list/profit-list.component';
import {RentedVehicleComponent} from './sites/rented-vehicle/rented-vehicle.component';


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'about-coop', component: AboutCoopComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'sign-in', component: SignInComponent},
  {path: 'driver-list', component: DriverListComponent, canActivate: [AuthGuard]},
  {path: 'add-driver', component: AddDriverComponent, canActivate: [AuthGuard]},
  {path: 'vehicle-list', component: VehicleListComponent, canActivate: [AuthGuard]},
  {path: 'add-vehicle', component: AddVehicleComponent, canActivate: [AuthGuard]},
  {path: 'profit-list', component: ProfitListComponent, canActivate: [AuthGuard]},
  {path: 'rented-vehicles', component: RentedVehicleComponent, canActivate: [AuthGuard]},
  {path: '**', redirectTo: ''}
];
// canActivate: [AuthGuard] dodaję to do path gdzie chcę mieć zalogowanie.
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
