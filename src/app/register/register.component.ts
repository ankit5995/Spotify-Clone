import { Component } from '@angular/core';

import { FormControl, FormGroupDirective, NgForm, Validators } from '@angular/forms';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = {
    email: "",
    password: "",
    password2: "",
    username: ""
  }

  constructor(
    private userService : UserService
  ){}

  isPasswordSame:boolean = false;
  checkPassword() {
    if(this.user.password === this.user.password2)
      this.isPasswordSame = true;
    else
      this.isPasswordSame = false;
    console.log(this.isPasswordSame,this.user.password,this.user.password2)
  }

  registerUser(){
      this.userService.registerUser(this.user).subscribe(data => alert("registration successfull"));
  }
}
