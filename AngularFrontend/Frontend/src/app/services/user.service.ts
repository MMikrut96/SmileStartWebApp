import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';

const TOKEN = 'TOKEN';
const USER = 'USER';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  setToken(token: string): void {
    localStorage.setItem(TOKEN, token);
  }

  setUser(user: string): void {
    localStorage.setItem(USER, user);
  }

  getUser(): String {
    return localStorage.getItem(USER);
  }

  getAuthHeader(): HttpHeaders {
    let headers: HttpHeaders = new HttpHeaders();
    headers = headers.append('Authorization', localStorage.getItem('TOKEN'));
    return headers;
  }
}
