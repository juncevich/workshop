import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  enabled = false;
  results = [];
  result = 0;

  changeState() {
    this.results.push(new Date());
    return this.enabled = !this.enabled;
  }
}
