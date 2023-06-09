import { Component, OnInit  } from '@angular/core';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {

  constructor(private auth: AuthService) {}

  isLoggedIn: boolean = false;

  ngOnInit(): void {
    this.auth.getStatus().subscribe(isLoggedIn => {
      this.isLoggedIn = isLoggedIn;
    })
  }

  logoutUser() {
    this.auth.logout();
  }

}
