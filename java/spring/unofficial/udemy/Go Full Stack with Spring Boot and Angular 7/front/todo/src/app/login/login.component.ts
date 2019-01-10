import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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

  constructor(private router: Router) { }

  ngOnInit() {
  }

  handleLogin() {
    // console.log(this.username);
    // console.log(this.password);
    if (this.username === 'alex' && this.password === 'test') {
      this.router.navigate(['welcome'])
      this.invalidLogin = false
    } else {
      this.invalidLogin = true
    }
  }
}
