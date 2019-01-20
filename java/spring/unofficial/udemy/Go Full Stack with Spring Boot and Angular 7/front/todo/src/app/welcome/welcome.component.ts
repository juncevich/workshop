import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WelcomeDataService } from '../service/data/welcome-data.service';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {
  welcomeMessageFromService: string;
  name = '';
  constructor(
    private route: ActivatedRoute,
    private welcomeDataService: WelcomeDataService
  ) { }

  ngOnInit() {
    this.name = this.route.snapshot.params['name'];
  }

  getWelcomeMessage() {
    console.log(this.welcomeDataService.executeHelloWorldBeanService());
    this.welcomeDataService.executeHelloWorldBeanService().subscribe(
      response => this.handleSuccessfullResponse(response)
    );
    console.log('last line of getWelcomeMessage');
  }

  handleSuccessfullResponse(response) {
    this.welcomeMessageFromService = response.message;
  }

}
