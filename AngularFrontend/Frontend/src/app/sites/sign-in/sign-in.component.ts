import {Component} from '@angular/core';
import {SignInServiceService} from '../../services/sign-in-service.service';
import {Router} from '@angular/router';
import {UserService} from '../../services/user.service';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {

  username;
  password;

  constructor(private api: SignInServiceService, private user: UserService, private router: Router) {
  }

  tryLogin() {
    this.api.login(this.username, this.password)
      .subscribe(res => {
          this.user.setUser(res.login);
          this.user.setToken(res.token);
          this.router.navigateByUrl('/');
        });
  }
}

