import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { User } from '../models/user';
import { RouteService } from './route.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private route: RouteService,private http: HttpClient) { }

  msg:string = '';
  jwt:string = '';
  isLoggedIn: boolean = false;
  URL: string = "http://localhost:9002/api/v1/login";

  getStatus(): Observable<boolean> {
    return of(this.isLoggedIn);
  }

  getToken(user:User){
    return this.http.post(this.URL,user);
  }
  getJwt(){
    return of(this.jwt);
  }

  login(user:any): any {
    this.getToken(user).subscribe(
      (data:any) => {
        sessionStorage.setItem("jwt",data.token);
          console.log(data.token);
          this.jwt = JSON.parse(JSON.stringify(data))["token"];
          this.msg = JSON.parse(JSON.stringify(data))["message"];
          console.log(this.msg);
          console.log(this.jwt);



          sessionStorage.setItem("msg",JSON.parse(JSON.stringify(data))["message"]);
          // sessionStorage.setItem("jwt",this.jwt);

          console.log(typeof(sessionStorage.getItem("jwt")));


        })


        if(sessionStorage.getItem("msg") === "Login Successful") {
          // alert("Success");
          // this.route.navigateToHome();
          this.isLoggedIn = true;
        }
        else {
          this.isLoggedIn = false;
        }
        console.log(this.isLoggedIn);

    return this.isLoggedIn;
  }

  logout(): void {
    sessionStorage.clear();
    this.isLoggedIn = false;
    this.route.navigateToLogin();
  }

}
