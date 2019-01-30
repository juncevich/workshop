import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

export class HelloWorldBean {
  constructor(public message: string) { }
}

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public executeHelloWorldBeanService() {
    return this.httpClient.get<HelloWorldBean>('http://localhost:8080/hello-world-bean');
  }

  // http://localhost:8080/hello-world/alex
  public executeHelloWorldBeanServiceWithPathVariable(name) {
    const basicAuthHeaderString = this.createBasicAuthenticationHttpHeader();
    const headers = new HttpHeaders({
      Authorization: basicAuthHeaderString
    });
    return this.httpClient.get<HelloWorldBean>(`http://localhost:8080/hello-world/${name}`, { headers });
  }

  createBasicAuthenticationHttpHeader() {
    const username = 'alex';
    const password = 'test';
    const basicAuthHeaderString = 'Basic ' + window.btoa(username + ':' + password);
    return basicAuthHeaderString;
  }
}
