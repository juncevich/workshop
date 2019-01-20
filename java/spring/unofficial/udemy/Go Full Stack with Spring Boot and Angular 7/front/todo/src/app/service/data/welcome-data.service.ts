import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor(
    private httpClient: HttpClient
  ) { }

  public executeHelloWorldBeanService() {
    return this.httpClient.get('http://localhost:8080/hello-world-bean');
  }
}
