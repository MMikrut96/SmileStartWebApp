import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Profit} from '../models/profit';
import {Observable} from 'rxjs/index';
import {UserService} from './user.service';
import {apiUrl} from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class ProfitServiceService {

  listUrl = 'my-profits?driverPesel=';
  addUrl = 'add-profit';
  saveUrl = 'save-profit';

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getMyProfits(): Observable<Profit[]> {
    return this.http.get<Profit[]>(apiUrl + this.listUrl + this.userService.getUser(), {headers: this.userService.getAuthHeader()});
  }

  addToMyProfits(profit: Profit): Observable<Profit> {
    return this.http.post<Profit>(apiUrl + this.addUrl, profit, {headers: this.userService.getAuthHeader()});
  }

  saveMyProfit(profit: Profit): Observable<Profit> {
    return this.http.post<Profit>(apiUrl + this.saveUrl, profit, {headers: this.userService.getAuthHeader()});
  }


}
