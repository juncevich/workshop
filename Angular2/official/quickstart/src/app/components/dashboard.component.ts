/**
 * Created by alex on 12.04.17.
 */
import {Component, OnInit} from '@angular/core';

import {Hero} from '../hero';
import {HeroService} from '../services/hero.service';
@Component({
  selector: 'my-dashboard',
  templateUrl: '../../view/dashboard.component.html',
  styleUrls: ['../../styles/dashboard.component.css']
})

export class DashboardComponent implements OnInit {
  heroes: Hero[] = [];

  constructor(private heroService: HeroService) {
  }

  ngOnInit(): void {
    this.heroService.getHeroes().then(heroes => this.heroes = heroes.slice(1, 5));
  }
}
