import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Driver} from '../models/driver';
import {Observable} from 'rxjs/index';
import {LoginResultModel} from '../models/login';
import {UserService} from './user.service';
import {apiUrl} from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class DriverService {
  getUrl = 'drivers';
  deleteUrl = 'delete-driver?pesel=';
  addUrl = 'add-driver';
  addUserUrl = 'add-user?login=';

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getDrivers(): Observable<Driver[]> {
    return this.http.get<Driver[]>(apiUrl + this.getUrl, {headers: this.userService.getAuthHeader()});
  }

  deleteDriver(driver: Driver): Observable<{}> {
    return this.http.post(apiUrl + this.deleteUrl + driver.pesel, {}, {headers: this.userService.getAuthHeader()});
  }

  addDriver(driver: Driver): Observable<Driver> {
    return this.http.post<Driver>(apiUrl + this.addUrl, driver, {headers: this.userService.getAuthHeader()});
  }

  addUser(pesel: String): Observable<LoginResultModel> {
    return this.http.post<LoginResultModel>(apiUrl + this.addUserUrl + pesel, {}, {headers: this.userService.getAuthHeader()});
  }
}
