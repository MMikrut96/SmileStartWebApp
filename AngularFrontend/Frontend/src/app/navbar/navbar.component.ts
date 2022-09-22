import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  private navtitle = 'SmileStart';

  constructor() {
  }

  isLoggedIn() {
    if (localStorage.hasOwnProperty('USER')) {
      return false;
    } else {
      return true;
    }
  }

  isAdmin() {
    if (localStorage.getItem('USER') === 'admin') {
      return true;
    } else {
      return false;
    }
  }

  logout() {
    localStorage.clear();
    this.ngOnInit();
  }

  ngOnInit() {
  }

}
