import {Component, OnInit} from '@angular/core';
import {UserService} from './user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  activeUsers: string[] = [];
  inactiveUsers: string[] = [];


  constructor(private userService: UserService) {
  }

  ngOnInit(): void {
    this.activeUsers = this.userService.activeUsers;
    this.inactiveUsers = this.userService.inactiveUsers;
  }

  onSetToInactive(id: number) {
    this.userService.addToInactive(id);
  }

  onSetToActive(id: number) {
    this.userService.addToActive(id);
  }
}
