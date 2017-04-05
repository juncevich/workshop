import { Component } from '@angular/core';

@Component({
  selector: 'my-app',
  template: `<h1>Hello {{name}}. It\`s test note</h1>`,
})
export class AppComponent  { name = 'Angular'; }
