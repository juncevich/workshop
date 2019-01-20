import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class WelcomeDataService {

  constructor() { }

  public executeHelloWorldBeanService() {
    console.log('Execute Hello World Bean Service');
  }
}
