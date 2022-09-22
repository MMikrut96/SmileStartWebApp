import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Vehicle} from '../models/vehicle';
import {Observable} from 'rxjs/index';
import {UserService} from './user.service';
import {apiUrl} from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class VehicleServiceService {

  getUrl = 'vehicles';
  deleteUrl = 'delete-vehicle';
  addUrl = 'add-vehicle';
  saveUrl = 'save-vehicle';

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getVehicles(): Observable<Vehicle[]> {
    return this.http.get<Vehicle[]>(apiUrl + this.getUrl, {headers: this.userService.getAuthHeader()});
  }

  deleteVehicle(vehicle: Vehicle): Observable<{}> {
    return this.http.post(apiUrl + this.deleteUrl, vehicle, {headers: this.userService.getAuthHeader()});
  }

  addVehicle(vehicle: Vehicle): Observable<Vehicle> {
    return this.http.post<Vehicle>(apiUrl + this.addUrl, vehicle, {headers: this.userService.getAuthHeader()});
  }

  saveVehicle(vehicle: Vehicle): Observable<Vehicle> {
    return this.http.post<Vehicle>(apiUrl + this.saveUrl, vehicle, {headers: this.userService.getAuthHeader()});
  }

  setRepair(vehicle: Vehicle): Observable<{}> {
    return this.http.post(apiUrl + this.saveUrl, vehicle, {headers: this.userService.getAuthHeader()});
  }
}
