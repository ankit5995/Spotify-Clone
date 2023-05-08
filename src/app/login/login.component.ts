import { Component } from '@angular/core';
import { User } from '../models/user';
import { AuthService } from '../services/auth.service';
import { RouteService } from '../services/route.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user:User = {
    email: "",
    password: ""
  }

  constructor(
    private route: RouteService,
    private auth: AuthService
  ) { }


  loginUser() {
    if(this.auth.login(this.user)) {
      alert("Success");
      this.route.navigateToHome();
    }
    else {
      alert("Wrong credentials!");
    }
    this.auth.login(this.user);
  }

}
