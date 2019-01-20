import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

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
}
