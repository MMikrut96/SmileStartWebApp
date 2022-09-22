import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/index';
import {apiUrl} from '../config/config';

@Injectable({
  providedIn: 'root'
})
export class SignInServiceService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post<any>(apiUrl + 'login?username=' + username + '&password=' + password, {});
  }
}
