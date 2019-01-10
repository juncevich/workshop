import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username = 'testUsername'
  password = ''
  errorMessage = 'InvalidCredentials'
  invalidLogin = false

  constructor() { }

  ngOnInit() {
  }

  handleLogin() {
    // console.log(this.username);
    // console.log(this.password);
    if (this.username === 'alex' && this.password === 'test') {
      this.invalidLogin = false
    } else {
      this.invalidLogin = true
    }
  }
}
