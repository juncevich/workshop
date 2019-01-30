import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BasicAuthenticationService {

  constructor(
    private http: HttpClient
  ) { }

  authenticate(username, password) {
    console.log('before ' + this.isUserLoggedIn());
    if (username === 'alex' && password === 'test') {
      sessionStorage.setItem('authenticatedUser', username);
      console.log('after ' + this.isUserLoggedIn());
      return true;
    }

    return false;
  }

  isUserLoggedIn() {
    const user = sessionStorage.getItem('authenticatedUser');
    return !(user === null);
  }

  logout() {
    sessionStorage.removeItem('authenticatedUser');
  }

  public executeBasicAuthenticationService(username, password) {

    const basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);

    const headers = new HttpHeaders({
      Authorization: basicAuthHeaderString
    });
    return this.http.get<AuthenticationBean>(`http://localhost:8080/basicauth`, { headers });
  }


}

export class AuthenticationBean {

  constructor(public message: string) {

  }
}
