import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../models/user';
import { BehaviorSubject, Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  URL: string = "http://localhost:9002/api/v2/register";
  URL1: string = "http://localhost:9002/api/v2/user";

  constructor(private http: HttpClient) { }

  registerUser(user: any) {
    return this.http.post(this.URL, user);
  }
  getUser(Jwt:string){
    const requestHeader = new HttpHeaders().set('Authorization','Bearer '+Jwt);
    return this.http.get(this.URL1,{'headers':requestHeader});
  }

}
