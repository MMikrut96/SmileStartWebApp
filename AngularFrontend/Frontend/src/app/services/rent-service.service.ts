import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Rent} from '../models/rent';
import {Observable} from 'rxjs/index';
import {Vehicle} from '../models/vehicle';
import {UserService} from './user.service';
import {apiUrl} from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class RentServiceService {

  driverUrl = 'my-rents?driverPesel=';
  actualUrl = 'vehicle-actual-rent?vehicleVin=';
  endUrl = 'end-rent?rentId=';
  addUrl = 'add-rent';

  constructor(private http: HttpClient, private userService: UserService) {
  }


  getDriverRent(driverPesel: String): Observable<Rent[]> {
    return this.http.get<Rent[]>(apiUrl + this.driverUrl + driverPesel, {headers: this.userService.getAuthHeader()});
  }

  getVehicleActualRent(vehicle: Vehicle): Observable<Rent> {
    return this.http.get<Rent>(apiUrl + this.actualUrl + vehicle.vin, {headers: this.userService.getAuthHeader()});
  }

  addRent(rent: Rent): Observable<Rent> {
    return this.http.post<Rent>(apiUrl + this.addUrl, rent, {headers: this.userService.getAuthHeader()});
  }

  endRent(rentId: number): Observable<{}> {
    return this.http.post(apiUrl + this.endUrl + rentId, {}, {headers: this.userService.getAuthHeader()});
  }

}
